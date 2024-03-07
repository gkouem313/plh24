/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author anton
 */
@Entity
@Table(name = "THECITY")
@NamedQueries({
    @NamedQuery(name = "Thecity.findAll", query = "SELECT t FROM Thecity t"),
    @NamedQuery(name = "Thecity.findByIdcity", query = "SELECT t FROM Thecity t WHERE t.idcity = :idcity"),
    @NamedQuery(name = "Thecity.findByTempC", query = "SELECT t FROM Thecity t WHERE t.tempC = :tempC"),
    @NamedQuery(name = "Thecity.findByHumidity", query = "SELECT t FROM Thecity t WHERE t.humidity = :humidity"),
    @NamedQuery(name = "Thecity.findByWindspeedkmph", query = "SELECT t FROM Thecity t WHERE t.windspeedkmph = :windspeedkmph"),
    @NamedQuery(name = "Thecity.findByUvindex", query = "SELECT t FROM Thecity t WHERE t.uvindex = :uvindex"),
    @NamedQuery(name = "Thecity.findByWeatherdesc", query = "SELECT t FROM Thecity t WHERE t.weatherdesc = :weatherdesc"),
    @NamedQuery(name = "Thecity.findByViews", query = "SELECT t FROM Thecity t WHERE t.views = :views")})
public class Thecity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDCITY")
    private Integer idcity;
    @Column(name = "TEMP_C")
    private String tempC;
    @Column(name = "HUMIDITY")
    private String humidity;
    @Column(name = "WINDSPEEDKMPH")
    private String windspeedkmph;
    @Column(name = "UVINDEX")
    private String uvindex;
    @Column(name = "WEATHERDESC")
    private String weatherdesc;
    @Column(name = "VIEWS")
    private Integer views;

    public Thecity() {
    }

    public Thecity(Integer idcity) {
        this.idcity = idcity;
    }

    public Integer getIdcity() {
        return idcity;
    }

    public void setIdcity(Integer idcity) {
        this.idcity = idcity;
    }

    public String getTempC() {
        return tempC;
    }

    public void setTempC(String tempC) {
        this.tempC = tempC;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindspeedkmph() {
        return windspeedkmph;
    }

    public void setWindspeedkmph(String windspeedkmph) {
        this.windspeedkmph = windspeedkmph;
    }

    public String getUvindex() {
        return uvindex;
    }

    public void setUvindex(String uvindex) {
        this.uvindex = uvindex;
    }

    public String getWeatherdesc() {
        return weatherdesc;
    }

    public void setWeatherdesc(String weatherdesc) {
        this.weatherdesc = weatherdesc;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcity != null ? idcity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Thecity)) {
            return false;
        }
        Thecity other = (Thecity) object;
        if ((this.idcity == null && other.idcity != null) || (this.idcity != null && !this.idcity.equals(other.idcity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Thecity[ idcity=" + idcity + " ]";
    }
    
}
