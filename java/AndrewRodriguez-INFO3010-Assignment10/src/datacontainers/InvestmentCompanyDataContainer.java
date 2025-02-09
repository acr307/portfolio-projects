/**
 *
 * @author arodrig7
 */
package datacontainers;

import datamodels.InvestmentCompany;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// Required to use JAXB XML library
@XmlRootElement(name = "InvestmentCompanyDataContainer")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvestmentCompanyDataContainer {
    
    /**
    * Simple container that stores elements as a list, can contain duplicates
    * Stores in FIFO order
    */
    // Required to use JAXB XML library
    @XmlElement(name = "investmentCompany") 
    private List<InvestmentCompany> companyList = new ArrayList<>();
    
    /**
    * Container that stores elements as a set of unique elements Random ordering
    */
   private Set companySet = new HashSet();

   /**
    * Container that stores elements as a map, can contain duplicates Order not
    * enforced
    */
   private Map companyMap = new HashMap();
    
    public List<InvestmentCompany> getInvestmentCompanyList() {
        return this.companyList;
    }
    
    public void setCompanyList(List<InvestmentCompany> companyList) {
        this.companyList = companyList;
    }
    
    public Set getCompanySet() {
        return companySet;
    }
    
    public void setCompanySet(Set companySet) {
        this.companySet = companySet;
    }
    
    public Map getCompanyMap() {
        return companyMap;
    }
    
    public void setCompanyMap(Map companyMap) {
        this.companyMap = companyMap;
    }
}
