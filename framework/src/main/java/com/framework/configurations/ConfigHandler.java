package com.framework.configurations;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;

import com.framework.core.models.Feature;
import com.framework.utils.PathUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;
import org.apache.commons.io.IOUtils;

public class ConfigHandler {

    public static JSONObject config = null;

    //public static ArrayList<String> devices = null;

    //public static ArrayList<Banner> banners = null;

    public static ArrayList<Feature> features = null;

    /**
     * initialize the configurations in app-config.json
     */
    public static void initConfigurations() {

        String configFile = PathUtils.getRootPath() + "/app-config.json";

        try {
            FileInputStream inputStream = new FileInputStream(configFile);

            String contents = IOUtils.toString(inputStream, "UTF-8");

            config = new JSONObject(contents);

            //setDevices();

           // setBanners();

            // setFeatures();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param keyName used to get value from config json object
     * @return String value from config json object
     */
    public static String getConfigVal(String keyName) {
        if (config == null) {
            return "";
        }
        return config.optString(keyName, "");
    }

    /**
     *
     * @param keyName used to get json object from config json object
     * @return Json object from config json object
     */
    public static JSONObject getConfigObj(String keyName) {
        if (config == null) {
            return new JSONObject();
        }
        return config.getJSONObject(keyName);
    }

    public static JSONArray getConfigArr(String keyName) {
        if (config == null) {
            return new JSONArray();
        }
        return config.getJSONArray(keyName);
    }

//    /**
//     * creates list of devices from config json
//     */
//    private static void setDevices() {
//
//        devices = new ArrayList<String>();
//
//        JSONArray array = config.getJSONArray("device");
//
//		IntStream.range(0, array.length()).mapToObj(array::getString).forEach(deviceName -> devices.add(deviceName));
//    }
//
//    /**
//     * creates list of banner from config json
//     */
//    private static void setBanners() {
//
//        banners = new ArrayList<Banner>();
//
//        Banner banner = null;
//
//        JSONArray array = config.getJSONArray("banner");
//
//        for (int i = 0; i < array.length(); i++) {
//
//            banner = new Banner();
//
//            banner.setName(array.getString(i));
//
//            banners.add(banner);
//        }
//    }

    /**
     * creates list of features from suites.xml using the suite name from config json
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private static <featureList> void setFeatures() throws ParserConfigurationException, SAXException, IOException {

       features = new ArrayList<>();

       Feature feature;

       String[] featureList = config.getJSONObject("browser").getString("features").split(",");

       for(int i =0; i < featureList.length; i++) {
           feature = new Feature();
           feature.setName(featureList[i]);
           features.add(feature);
       }


//        features = new ArrayList<Feature>();
//        Feature feature = null;
//        String suiteName = config.optString("suite");
//        String configFile = PathUtils.getRootPath() + "/suites.xml";
//        File xmlFile = new File(configFile);
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//
//        Document doc = dBuilder.parse(xmlFile);
//        doc.getDocumentElement().normalize();
//
//        NodeList nList = doc.getElementsByTagName("suite");
//
//        for (int i = 0; i < nList.getLength(); i++) {
//
//            Node node = nList.item(i);
//
//            Element element = (Element) node;
//
//            if (element.getAttribute("name").toLowerCase().equalsIgnoreCase(suiteName.toLowerCase())) {
//
//                NodeList featureNodes = element.getChildNodes();
//
//                for (int x = 0; x < featureNodes.getLength(); x++) {
//
//                    feature = new Feature();
//
//                    Node featureNode = featureNodes.item(x);
//                    if (featureNode.getNodeType() == Node.ELEMENT_NODE) {
//
//                        NamedNodeMap nodeMap = featureNode.getAttributes();
//
//                        for (int a = 0; a < nodeMap.getLength(); a++) {
//
//                            Node attributeNode = nodeMap.item(a);
//
//                            if (attributeNode.getNodeName().equalsIgnoreCase("name")) {
//
//                                feature.setName(attributeNode.getNodeValue());
//
//                            } else if (attributeNode.getNodeName().equalsIgnoreCase("datasource")) {
//
//                                feature.setDatasource(attributeNode.getNodeValue());
//
//                            } else if (attributeNode.getNodeName().equalsIgnoreCase("datarow")) {
//
//                                feature.setDatarow(attributeNode.getNodeValue());
//                            }
//                        }
//
//                        features.add(feature);
//                    }
//                }
//            }
//        }
    }
}
