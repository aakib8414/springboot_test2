package com.test.test1.service;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class XServerKeyGenerator {

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public static X509Certificate generateCertificate(KeyPair keyPair) throws OperatorCreationException, CertificateException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        X500Name issuer = new X500Name("CN=Example CA");
        X500Name subject = new X500Name("CN=Example Server");

        BigInteger serialNumber = BigInteger.valueOf(System.currentTimeMillis());

        Instant now = Instant.now();
        Date startDate = Date.from(now);
        Date endDate = Date.from(now.plus(Duration.ofDays(365)));

        X509v3CertificateBuilder certificateBuilder = new JcaX509v3CertificateBuilder(
                issuer,
                serialNumber,
                startDate,
                endDate,
                subject,
                keyPair.getPublic()
        );

        X509CertificateHolder certificateHolder = certificateBuilder.build(new JcaContentSignerBuilder("SHA256withRSA").build(keyPair.getPrivate()));
        return new JcaX509CertificateConverter().getCertificate(certificateHolder);
    }

    public static void main(String[] args) {
        try {
            KeyPair keyPair = generateKeyPair();
            X509Certificate certificate = generateCertificate(keyPair);

            // Use the keyPair and certificate as needed
            System.out.println("Private Key: " + keyPair.getPrivate());
            System.out.println("Public Key: " + keyPair.getPublic());
            System.out.println("Certificate: " + certificate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

