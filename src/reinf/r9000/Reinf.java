//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.10 at 02:20:22 PM BRT 
//


package reinf.r9000;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="evtExclusao">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ideEvento">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="tpAmb">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedInt">
 *                                   &lt;pattern value="[1|2]"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="procEmi">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedByte">
 *                                   &lt;pattern value="1|2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="verProc">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;minLength value="1"/>
 *                                   &lt;maxLength value="20"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="ideContri">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="tpInsc">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedByte">
 *                                   &lt;pattern value="1|2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="nrInsc">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;minLength value="8"/>
 *                                   &lt;maxLength value="14"/>
 *                                   &lt;pattern value="\d{8}|\d{11}|\d{14}"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="infoExclusao">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="tpEvento">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="6"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="nrRecEvt">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;minLength value="16"/>
 *                                   &lt;maxLength value="52"/>
 *                                   &lt;pattern value="[0-9]{1,18}[-][0-9]{2}[-][0-9]{4}[-][0-9]{4,6}[-][0-9]{1,18}"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="perApur">
 *                               &lt;simpleType>
 *                                 &lt;union>
 *                                   &lt;simpleType>
 *                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}gYearMonth">
 *                                       &lt;pattern value="2{1}[0-9]{3}-{1}[0-1]{1}[0-9]{1}"/>
 *                                     &lt;/restriction>
 *                                   &lt;/simpleType>
 *                                   &lt;simpleType>
 *                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}date">
 *                                       &lt;pattern value="2{1}[0-9]{3}-{1}[0-1]{1}[0-9]{1}-{1}[0-3]{1}[0-9]{1}"/>
 *                                     &lt;/restriction>
 *                                   &lt;/simpleType>
 *                                 &lt;/union>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="id" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}ID">
 *                       &lt;length value="36"/>
 *                       &lt;pattern value="I{1}D{1}[0-9]{34}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "evtExclusao",
    "signature"
})
@XmlRootElement(name = "Reinf", namespace = "http://www.reinf.esocial.gov.br/schemas/evtExclusao/v1_03_02")
public class Reinf {

    @XmlElement(namespace = "http://www.reinf.esocial.gov.br/schemas/evtExclusao/v1_03_02", required = true)
    protected Reinf.EvtExclusao evtExclusao;
    @XmlElement(name = "Signature", required = true)
    protected SignatureType signature;

    /**
     * Gets the value of the evtExclusao property.
     * 
     * @return
     *     possible object is
     *     {@link Reinf.EvtExclusao }
     *     
     */
    public Reinf.EvtExclusao getEvtExclusao() {
        return evtExclusao;
    }

    /**
     * Sets the value of the evtExclusao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reinf.EvtExclusao }
     *     
     */
    public void setEvtExclusao(Reinf.EvtExclusao value) {
        this.evtExclusao = value;
    }

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ideEvento">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="tpAmb">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedInt">
     *                         &lt;pattern value="[1|2]"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="procEmi">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedByte">
     *                         &lt;pattern value="1|2"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="verProc">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;minLength value="1"/>
     *                         &lt;maxLength value="20"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="ideContri">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="tpInsc">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedByte">
     *                         &lt;pattern value="1|2"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="nrInsc">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;minLength value="8"/>
     *                         &lt;maxLength value="14"/>
     *                         &lt;pattern value="\d{8}|\d{11}|\d{14}"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="infoExclusao">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="tpEvento">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="6"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="nrRecEvt">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;minLength value="16"/>
     *                         &lt;maxLength value="52"/>
     *                         &lt;pattern value="[0-9]{1,18}[-][0-9]{2}[-][0-9]{4}[-][0-9]{4,6}[-][0-9]{1,18}"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="perApur">
     *                     &lt;simpleType>
     *                       &lt;union>
     *                         &lt;simpleType>
     *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}gYearMonth">
     *                             &lt;pattern value="2{1}[0-9]{3}-{1}[0-1]{1}[0-9]{1}"/>
     *                           &lt;/restriction>
     *                         &lt;/simpleType>
     *                         &lt;simpleType>
     *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}date">
     *                             &lt;pattern value="2{1}[0-9]{3}-{1}[0-1]{1}[0-9]{1}-{1}[0-3]{1}[0-9]{1}"/>
     *                           &lt;/restriction>
     *                         &lt;/simpleType>
     *                       &lt;/union>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="id" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}ID">
     *             &lt;length value="36"/>
     *             &lt;pattern value="I{1}D{1}[0-9]{34}"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "ideEvento",
        "ideContri",
        "infoExclusao"
    })
    public static class EvtExclusao {

        @XmlElement(namespace = "http://www.reinf.esocial.gov.br/schemas/evtExclusao/v1_03_02", required = true)
        protected Reinf.EvtExclusao.IdeEvento ideEvento;
        @XmlElement(namespace = "http://www.reinf.esocial.gov.br/schemas/evtExclusao/v1_03_02", required = true)
        protected Reinf.EvtExclusao.IdeContri ideContri;
        @XmlElement(namespace = "http://www.reinf.esocial.gov.br/schemas/evtExclusao/v1_03_02", required = true)
        protected Reinf.EvtExclusao.InfoExclusao infoExclusao;
        @XmlAttribute(name = "id", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        protected String id;

        /**
         * Gets the value of the ideEvento property.
         * 
         * @return
         *     possible object is
         *     {@link Reinf.EvtExclusao.IdeEvento }
         *     
         */
        public Reinf.EvtExclusao.IdeEvento getIdeEvento() {
            return ideEvento;
        }

        /**
         * Sets the value of the ideEvento property.
         * 
         * @param value
         *     allowed object is
         *     {@link Reinf.EvtExclusao.IdeEvento }
         *     
         */
        public void setIdeEvento(Reinf.EvtExclusao.IdeEvento value) {
            this.ideEvento = value;
        }

        /**
         * Gets the value of the ideContri property.
         * 
         * @return
         *     possible object is
         *     {@link Reinf.EvtExclusao.IdeContri }
         *     
         */
        public Reinf.EvtExclusao.IdeContri getIdeContri() {
            return ideContri;
        }

        /**
         * Sets the value of the ideContri property.
         * 
         * @param value
         *     allowed object is
         *     {@link Reinf.EvtExclusao.IdeContri }
         *     
         */
        public void setIdeContri(Reinf.EvtExclusao.IdeContri value) {
            this.ideContri = value;
        }

        /**
         * Gets the value of the infoExclusao property.
         * 
         * @return
         *     possible object is
         *     {@link Reinf.EvtExclusao.InfoExclusao }
         *     
         */
        public Reinf.EvtExclusao.InfoExclusao getInfoExclusao() {
            return infoExclusao;
        }

        /**
         * Sets the value of the infoExclusao property.
         * 
         * @param value
         *     allowed object is
         *     {@link Reinf.EvtExclusao.InfoExclusao }
         *     
         */
        public void setInfoExclusao(Reinf.EvtExclusao.InfoExclusao value) {
            this.infoExclusao = value;
        }

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }


        /**
         * Informa��es de identifica��o do contribuinte
         * 
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="tpInsc">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedByte">
         *               &lt;pattern value="1|2"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="nrInsc">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;minLength value="8"/>
         *               &lt;maxLength value="14"/>
         *               &lt;pattern value="\d{8}|\d{11}|\d{14}"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "tpInsc",
            "nrInsc"
        })
        public static class IdeContri {

            @XmlElement(namespace = "http://www.reinf.esocial.gov.br/schemas/evtExclusao/v1_03_02")
            protected short tpInsc;
            @XmlElement(namespace = "http://www.reinf.esocial.gov.br/schemas/evtExclusao/v1_03_02", required = true)
            protected String nrInsc;

            /**
             * Gets the value of the tpInsc property.
             * 
             */
            public short getTpInsc() {
                return tpInsc;
            }

            /**
             * Sets the value of the tpInsc property.
             * 
             */
            public void setTpInsc(short value) {
                this.tpInsc = value;
            }

            /**
             * Gets the value of the nrInsc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNrInsc() {
                return nrInsc;
            }

            /**
             * Sets the value of the nrInsc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNrInsc(String value) {
                this.nrInsc = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="tpAmb">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedInt">
         *               &lt;pattern value="[1|2]"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="procEmi">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedByte">
         *               &lt;pattern value="1|2"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="verProc">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;minLength value="1"/>
         *               &lt;maxLength value="20"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "tpAmb",
            "procEmi",
            "verProc"
        })
        public static class IdeEvento {

            @XmlElement(namespace = "http://www.reinf.esocial.gov.br/schemas/evtExclusao/v1_03_02")
            protected long tpAmb;
            @XmlElement(namespace = "http://www.reinf.esocial.gov.br/schemas/evtExclusao/v1_03_02")
            protected short procEmi;
            @XmlElement(namespace = "http://www.reinf.esocial.gov.br/schemas/evtExclusao/v1_03_02", required = true)
            protected String verProc;

            /**
             * Gets the value of the tpAmb property.
             * 
             */
            public long getTpAmb() {
                return tpAmb;
            }

            /**
             * Sets the value of the tpAmb property.
             * 
             */
            public void setTpAmb(long value) {
                this.tpAmb = value;
            }

            /**
             * Gets the value of the procEmi property.
             * 
             */
            public short getProcEmi() {
                return procEmi;
            }

            /**
             * Sets the value of the procEmi property.
             * 
             */
            public void setProcEmi(short value) {
                this.procEmi = value;
            }

            /**
             * Gets the value of the verProc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVerProc() {
                return verProc;
            }

            /**
             * Sets the value of the verProc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVerProc(String value) {
                this.verProc = value;
            }

        }


        /**
         * Registro que identifica o evento objeto da exclus�o
         * 
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="tpEvento">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="6"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="nrRecEvt">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;minLength value="16"/>
         *               &lt;maxLength value="52"/>
         *               &lt;pattern value="[0-9]{1,18}[-][0-9]{2}[-][0-9]{4}[-][0-9]{4,6}[-][0-9]{1,18}"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="perApur">
         *           &lt;simpleType>
         *             &lt;union>
         *               &lt;simpleType>
         *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}gYearMonth">
         *                   &lt;pattern value="2{1}[0-9]{3}-{1}[0-1]{1}[0-9]{1}"/>
         *                 &lt;/restriction>
         *               &lt;/simpleType>
         *               &lt;simpleType>
         *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}date">
         *                   &lt;pattern value="2{1}[0-9]{3}-{1}[0-1]{1}[0-9]{1}-{1}[0-3]{1}[0-9]{1}"/>
         *                 &lt;/restriction>
         *               &lt;/simpleType>
         *             &lt;/union>
         *           &lt;/simpleType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "tpEvento",
            "nrRecEvt",
            "perApur"
        })
        public static class InfoExclusao {

            @XmlElement(namespace = "http://www.reinf.esocial.gov.br/schemas/evtExclusao/v1_03_02", required = true)
            protected String tpEvento;
            @XmlElement(namespace = "http://www.reinf.esocial.gov.br/schemas/evtExclusao/v1_03_02", required = true)
            protected String nrRecEvt;
            @XmlElement(namespace = "http://www.reinf.esocial.gov.br/schemas/evtExclusao/v1_03_02", required = true)
            protected String perApur;

            /**
             * Gets the value of the tpEvento property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTpEvento() {
                return tpEvento;
            }

            /**
             * Sets the value of the tpEvento property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTpEvento(String value) {
                this.tpEvento = value;
            }

            /**
             * Gets the value of the nrRecEvt property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNrRecEvt() {
                return nrRecEvt;
            }

            /**
             * Sets the value of the nrRecEvt property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNrRecEvt(String value) {
                this.nrRecEvt = value;
            }

            /**
             * Gets the value of the perApur property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPerApur() {
                return perApur;
            }

            /**
             * Sets the value of the perApur property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPerApur(String value) {
                this.perApur = value;
            }

        }

    }

}