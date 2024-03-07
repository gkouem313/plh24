/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author anton
 */
@Entity
@Table(name = "CITY")
@NamedQueries({
    @NamedQuery(name = "City.findAll", query = "SELECT c FROM City c"),
    @NamedQuery(name = "City.findByAreaname", query = "SELECT c FROM City c WHERE c.areaname = :areaname"),
    @NamedQuery(name = "City.findByTempC", query = "SELECT c FROM City c WHERE c.temp_C = :temp_C"),
    @NamedQuery(name = "City.findByHumidity", query = "SELECT c FROM City c WHERE c.humidity = :humidity"),
    @NamedQuery(name = "City.findByWindspeedkmph", query = "SELECT c FROM City c WHERE c.windspeedkmph = :windspeedkmph"),
    @NamedQuery(name = "City.findByUvindex", query = "SELECT c FROM City c WHERE c.uvindex = :uvindex"),
    @NamedQuery(name = "City.findByWatherdesc", query = "SELECT c FROM City c WHERE c.weatherdesc = :weatherdesc")})
public class City implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AREANAME")
    private String areaname;
    @Column(name = "TEMP_C")
    private String temp_C;
    @Column(name = "HUMIDITY")
    private String humidity;
    @Column(name = "WINDSPEEDKMPH")
    private String windspeedkmph;
    @Column(name = "UVINDEX")
    private String uvindex;
    @Column(name = "WATHERDESC")
    private String weatherdesc;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "city")
    private Cityview cityview;

    public City() {
    }
    
    public City(String areaname, String temp_C, String humidity, String windspeedkmph, String uvindex, String weatherdesc) {
    this.areaname = areaname;
    this.temp_C = temp_C;
    this.humidity= humidity;
    this.windspeedkmph = windspeedkmph;
    this.uvindex = uvindex;
    this.weatherdesc = weatherdesc;
    
    }

    public City(String areaname) {
        this.areaname = areaname;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getTempC() {
        return temp_C;
    }

    public void setTempC(String temp_C) {
        this.temp_C = temp_C;
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

    public Cityview getCityview() {
        return cityview;
    }

    public void setCityview(Cityview cityview) {
        this.cityview = cityview;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (areaname != null ? areaname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof City)) {
            return false;
        }
        City other = (City) object;
        if ((this.areaname == null && other.areaname != null) || (this.areaname != null && !this.areaname.equals(other.areaname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.City[ areaname=" + areaname + " ]";
    }
    
}
