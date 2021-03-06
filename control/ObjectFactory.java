//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.10.05 at 02:20:16 PM GMT-03:00 
//


package control;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the control package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Signature_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Signature");
    private final static QName _EnvDPEC_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "envDPEC");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: control
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TConsDPEC }
     * 
     */
    public TConsDPEC createTConsDPEC() {
        return new TConsDPEC();
    }

    /**
     * Create an instance of {@link SignatureType }
     * 
     */
    public SignatureType createSignatureType() {
        return new SignatureType();
    }

    /**
     * Create an instance of {@link KeyInfoType }
     * 
     */
    public KeyInfoType createKeyInfoType() {
        return new KeyInfoType();
    }

    /**
     * Create an instance of {@link TDPEC }
     * 
     */
    public TDPEC createTDPEC() {
        return new TDPEC();
    }

    /**
     * Create an instance of {@link TDPEC.InfDPEC.ResNFe }
     * 
     */
    public TDPEC.InfDPEC.ResNFe createTDPECInfDPECResNFe() {
        return new TDPEC.InfDPEC.ResNFe();
    }

    /**
     * Create an instance of {@link SignatureValueType }
     * 
     */
    public SignatureValueType createSignatureValueType() {
        return new SignatureValueType();
    }

    /**
     * Create an instance of {@link TRetConsDPEC }
     * 
     */
    public TRetConsDPEC createTRetConsDPEC() {
        return new TRetConsDPEC();
    }

    /**
     * Create an instance of {@link TDPEC.InfDPEC.IdeDec }
     * 
     */
    public TDPEC.InfDPEC.IdeDec createTDPECInfDPECIdeDec() {
        return new TDPEC.InfDPEC.IdeDec();
    }

    /**
     * Create an instance of {@link SignedInfoType.SignatureMethod }
     * 
     */
    public SignedInfoType.SignatureMethod createSignedInfoTypeSignatureMethod() {
        return new SignedInfoType.SignatureMethod();
    }

    /**
     * Create an instance of {@link SignedInfoType.CanonicalizationMethod }
     * 
     */
    public SignedInfoType.CanonicalizationMethod createSignedInfoTypeCanonicalizationMethod() {
        return new SignedInfoType.CanonicalizationMethod();
    }

    /**
     * Create an instance of {@link TransformType }
     * 
     */
    public TransformType createTransformType() {
        return new TransformType();
    }

    /**
     * Create an instance of {@link TRetDPEC }
     * 
     */
    public TRetDPEC createTRetDPEC() {
        return new TRetDPEC();
    }

    /**
     * Create an instance of {@link ReferenceType.DigestMethod }
     * 
     */
    public ReferenceType.DigestMethod createReferenceTypeDigestMethod() {
        return new ReferenceType.DigestMethod();
    }

    /**
     * Create an instance of {@link TDPEC.InfDPEC }
     * 
     */
    public TDPEC.InfDPEC createTDPECInfDPEC() {
        return new TDPEC.InfDPEC();
    }

    /**
     * Create an instance of {@link TRetDPEC.InfDPECReg }
     * 
     */
    public TRetDPEC.InfDPECReg createTRetDPECInfDPECReg() {
        return new TRetDPEC.InfDPECReg();
    }

    /**
     * Create an instance of {@link TransformsType }
     * 
     */
    public TransformsType createTransformsType() {
        return new TransformsType();
    }

    /**
     * Create an instance of {@link X509DataType }
     * 
     */
    public X509DataType createX509DataType() {
        return new X509DataType();
    }

    /**
     * Create an instance of {@link ReferenceType }
     * 
     */
    public ReferenceType createReferenceType() {
        return new ReferenceType();
    }

    /**
     * Create an instance of {@link SignedInfoType }
     * 
     */
    public SignedInfoType createSignedInfoType() {
        return new SignedInfoType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignatureType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Signature")
    public JAXBElement<SignatureType> createSignature(SignatureType value) {
        return new JAXBElement<SignatureType>(_Signature_QNAME, SignatureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TDPEC }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "envDPEC")
    public JAXBElement<TDPEC> createEnvDPEC(TDPEC value) {
        return new JAXBElement<TDPEC>(_EnvDPEC_QNAME, TDPEC.class, null, value);
    }

}
