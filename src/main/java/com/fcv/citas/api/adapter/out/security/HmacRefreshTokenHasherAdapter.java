package com.fcv.citas.api.adapter.out.security;

import com.fcv.citas.api.application.port.out.RefreshTokenHasherPort;
import com.fcv.citas.api.config.JwtProperties;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;

/**
 * El refresh token es opaco (no JWT). Se guarda en BD como HMAC-SHA256 del valor crudo usando
 * JWT_REFRESH_SECRET como clave, para que una fuga de la BD no permita reconstruir ni validar
 * tokens sin conocer también el secreto.
 */
@Component
public class HmacRefreshTokenHasherAdapter implements RefreshTokenHasherPort {

    private static final String ALGORITHM = "HmacSHA256";

    private final JwtProperties jwtProperties;

    public HmacRefreshTokenHasherAdapter(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public String hash(String rawRefreshToken) {
        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            mac.init(new SecretKeySpec(
                    jwtProperties.refreshSecret().getBytes(StandardCharsets.UTF_8), ALGORITHM));
            byte[] digest = mac.doFinal(rawRefreshToken.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(digest);
        } catch (Exception e) {
            throw new IllegalStateException("No fue posible calcular el hash del refresh token", e);
        }
    }
}
