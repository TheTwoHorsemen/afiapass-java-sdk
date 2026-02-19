package org.afiapass.infrastructure.security.jwt;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.afiapass.core.domain.data.models.Permit;
import org.afiapass.core.ports.outbound.TokenSigner;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.fill;


import java.util.Date;


public class NimbusJwtSigner implements TokenSigner {

    private final KeyManager keyManager;

    public NimbusJwtSigner(KeyManager keyManager) {
        this.keyManager = keyManager;
    }

    @Override
    public String generateOfflineToken(Permit permit) {
        try {
            char[] secretChars = keyManager.getPlatformKeyPair().getSecretSeed();
            byte[] secretBytes = new String(secretChars).getBytes(UTF_8);
            JWSSigner signer = new MACSigner(secretBytes);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(permit.riderId())
                    .issuer("AfiaPass-Truth-Engine")
                    .jwtID(permit.id().toString())
                    .claim("route", permit.routeId())
                    .claim("amount", permit.amount())
                    .claim("txHash", permit.stellarTxHash())
                    .issueTime(Date.from(permit.issuedAt()))
                    .expirationTime(Date.from(permit.expiresAt()))
                    .build();

            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256),
                    claimsSet
            );
            signedJWT.sign(signer);

            fill(secretChars, '\0');
            fill(secretBytes, (byte) 0);

            // Return the Base64 String (This becomes the QR Code)
            return signedJWT.serialize();

        } catch (Exception e) {
            throw new RuntimeException("Failed to sign offline permit token", e);
        }
    }
}