/**
 *
 * @author arodrig7
 */
package datacontainers;

import datamodels.Broker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// Required to use JAXB XML library
@XmlRootElement(name = "BrokerDataContainer")
@XmlAccessorType(XmlAccessType.FIELD)
public class BrokerDataContainer {
    
    /**
    * Simple container that stores elements as a list, can contain duplicates
    * Stores in FIFO order
    */
    // Required to use JAXB XML library
    @XmlElement(name = "broker") 
    private List<Broker> brokerList = new ArrayList<>();
    
    /**
    * Container that stores elements as a set of unique elements Random ordering
    */
   private Set brokerSet = new HashSet();

   /**
    * Container that stores elements as a map, can contain duplicates Order not
    * enforced
    */
   private Map brokerMap = new HashMap();
    
    public List<Broker> getBrokerList() {
        return brokerList;
    }
    
    public void setBrokerList(List<Broker> brokerList) {
        this.brokerList = brokerList;
    }
    
    public Set getBrokerSet() {
        return brokerSet;
    }
    
    public void setBrokerSet(Set brokerSet) {
        this.brokerSet = brokerSet;
    }
    
    public Map getBrokerMap() {
        return brokerMap;
    }
    
    public void setBrokerMap(Map brokerMap) {
        this.brokerMap = brokerMap;
    }
}
