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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author anton
 */
@Entity
@Table(name = "CITYVIEW")
@NamedQueries({
    @NamedQuery(name = "Cityview.findAll", query = "SELECT c FROM Cityview c"),
    @NamedQuery(name = "Cityview.findByViewcount", query = "SELECT c FROM Cityview c WHERE c.viewcount = :viewcount"),
    @NamedQuery(name = "Cityview.findByCityAreaname", query = "SELECT c FROM Cityview c WHERE c.cityAreaname = :cityAreaname")})
public class Cityview implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "VIEWCOUNT")
    private Integer viewcount;
    @Id
    @Basic(optional = false)
    @Column(name = "CITY_AREANAME")
    private String cityAreaname;
    @JoinColumn(name = "CITY_AREANAME", referencedColumnName = "AREANAME", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private City city;

    public Cityview() {
    }

    public Cityview(String cityAreaname) {
        this.cityAreaname = cityAreaname;
    }

    public Integer getViewcount() {
        return viewcount;
    }

    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    public String getCityAreaname() {
        return cityAreaname;
    }

    public void setCityAreaname(String cityAreaname) {
        this.cityAreaname = cityAreaname;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cityAreaname != null ? cityAreaname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cityview)) {
            return false;
        }
        Cityview other = (Cityview) object;
        if ((this.cityAreaname == null && other.cityAreaname != null) || (this.cityAreaname != null && !this.cityAreaname.equals(other.cityAreaname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cityview[ cityAreaname=" + cityAreaname + " ]";
    }
    
}
