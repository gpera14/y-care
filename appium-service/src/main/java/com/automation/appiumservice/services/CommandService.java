        package com.automation.appiumservice.services;

        import com.automation.appiumservice.exception.BadRequestException;
        import com.automation.appiumservice.exception.DriverNotFoundException;
        import com.automation.appiumservice.exception.ElementNotFoundException;
        import com.automation.appiumservice.exception.MethodNotFoundException;
        import com.automation.appiumservice.helpers.DriverHelper;
        import io.appium.java_client.AppiumDriver;
        import io.appium.java_client.MobileBy;
        import io.appium.java_client.MobileElement;
        import io.appium.java_client.TouchAction;
        import org.openqa.selenium.interactions.Actions;
        import org.openqa.selenium.interactions.touch.TouchActions;
        import org.springframework.stereotype.Service;

        import javax.swing.*;
        import java.time.Duration;
        import java.util.Map;

        @Service
        public class CommandService implements ICommandService{

            AppiumDriver driver;

            @Override
            public String execute(Map<String, Object> request) throws Exception {


                String sessionId = request.get("sessionId").toString();

                driver = DriverHelper.getDriver(sessionId);


                Map<String, Object> data = (Map<String, Object>) request.get("data");

                String methodName = request.get("methodName").toString();
                String result="";
                if (data.isEmpty()) {
                    if (methodName.equalsIgnoreCase("click")) {

                        findElement(data.get("").toString(), data.get("").toString()).click();
                        result="true";

                    } else if (methodName.equalsIgnoreCase("sendKeys")) {
                        findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()).sendKeys(data.get("sendkeydata").toString());
                        result="true";
                    } else if (methodName.equalsIgnoreCase("clear")) {
                        findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()).clear();
                        result="true";

                    } else if (methodName.equalsIgnoreCase("isSelected")) {
                        result= String.valueOf(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()).isSelected());
                    } else if (methodName.equalsIgnoreCase("isEnabled")) {
                        result= String.valueOf(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()).isEnabled());
                    } else if (methodName.equalsIgnoreCase("isDisplayed")) {
                        result= String.valueOf(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()).isDisplayed());
                    }
                    else if (methodName.equalsIgnoreCase("getText")) {
                        result= String.valueOf(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()).getText());
                    }
                    else if (methodName.equalsIgnoreCase("getTagName")) {
                        result= String.valueOf(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()).getTagName());
                    }
                    else if (methodName.equalsIgnoreCase("getAttribute")) {
                        result= String.valueOf(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()).getAttribute(data.get("getAttributeData").toString()));
                    }
                    else if (methodName.equalsIgnoreCase("getId")) {
                        result= String.valueOf(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()).getId());
                    }
                    else if (methodName.equalsIgnoreCase("getContext")) {
                        result= String.valueOf(driver.getContext());
                    }
                    else if (methodName.equalsIgnoreCase("context")) {
                        result= String.valueOf(driver.context(data.get("contextData").toString()));
                    }
                    else if (methodName.equalsIgnoreCase("moveTo")) {
                        Actions action =new Actions(driver);

                        action.moveToElement(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()));
                        action.perform();
                        result="true";
                    }
                    else if (methodName.equalsIgnoreCase("moveToElement")) {
                        Actions action =new Actions(driver);
                        action.moveToElement(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()),Integer.valueOf(data.get("moveTodataX").toString()),Integer.valueOf(data.get("moveTodataY").toString()));
                        action.perform();
                        result="true";
                    }
               
                    else if (methodName.equalsIgnoreCase("mouseClick")) {
                        Actions action =new Actions(driver);
                        action.moveToElement(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()));
                        action.click();
                        action.perform();
                        result="true";

                    }
                    else if (methodName.equalsIgnoreCase("mouseClickElement")) {
                        Actions action =new Actions(driver);
                        action.moveToElement(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()));
                        action.click(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()));
                        action.perform();
                        result="true";

                    }
                    else if (methodName.equalsIgnoreCase("mouseDblClick")) {
                        Actions action =new Actions(driver);
                        action.moveToElement(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()));
                        action.doubleClick();
                        action.perform();
                        result="true";

                    }
                    else if (methodName.equalsIgnoreCase("singleTap")) {
                        TouchActions action = new TouchActions(driver);
                        action.singleTap(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()));
                        action.perform();
                        result="true";
                    }
                    else if (methodName.equalsIgnoreCase("doubleTap")) {
                        TouchActions action = new TouchActions(driver);
                        action.doubleTap(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()));
                        action.perform();
                        result="true";
                    }
                    else if (methodName.equalsIgnoreCase("longTap")) {
                        TouchActions action = new TouchActions(driver);
                        action.longPress(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()));
                        action.perform();
                        result="true";
                    }
                    else if (methodName.equalsIgnoreCase("move")) {
                        TouchActions action = new TouchActions(driver);
                        action.down(Integer.valueOf(data.get("moveTodataX").toString()),Integer.valueOf(data.get("moveTodataY").toString()));
                        action.move(Integer.valueOf(data.get("moveTodataX").toString()),Integer.valueOf(data.get("moveTodataY").toString()));
                        action.perform();
                        result="true";
                    }
                    else if (methodName.equalsIgnoreCase("moveTo")) {
                        TouchActions action = new TouchActions(driver);
                        action.down(Integer.valueOf(data.get("moveTodataX").toString()),Integer.valueOf(data.get("moveTodataY").toString()));
                        action.move(Integer.valueOf(data.get("moveTodataX").toString()),Integer.valueOf(data.get("moveTodataY").toString()));
                        action.perform();
                        result="true";
                    }
                    else if (methodName.equalsIgnoreCase("touchUp")) {
                        TouchActions action = new TouchActions(driver);
                        action.down(Integer.valueOf(data.get("moveTodataX").toString()),Integer.valueOf(data.get("moveTodataY").toString()));
                        action.up(Integer.valueOf(data.get("moveTodataX").toString()),Integer.valueOf(data.get("moveTodataY").toString()));
                        action.perform();
                        result="true";
                    }
                    else if (methodName.equalsIgnoreCase("scroll")) {
                        TouchActions action =new TouchActions(driver);
                        action.scroll(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()),Integer.valueOf(data.get("moveTodataX").toString()),Integer.valueOf(data.get("moveTodataY").toString()));
                        action.perform();
                        result="true";
                    }
                    else if (methodName.equalsIgnoreCase("scrollTouch")) {
                        TouchActions action =new TouchActions(driver);
                        action.scroll(Integer.valueOf(data.get("moveTodataX").toString()),Integer.valueOf(data.get("moveTodataY").toString()));
                        action.perform();
                        result="true";
                    }


                    else if (methodName.equalsIgnoreCase("mouseDblClickElement")) {
                        Actions action =new Actions(driver);
                        action.moveToElement(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()));
                        action.doubleClick(findElement(data.get("stratergyKey").toString(), data.get("stratergyValue").toString()));
                        action.perform();
                        result="true";

                    }
                    else if (methodName.equalsIgnoreCase("installApp"))
                    {
                        driver.installApp(data.get("installAppData").toString());
                        result="true";
                    }
                    else if(methodName.equalsIgnoreCase("isAppInstalled")){
                        result=String.valueOf(driver.isAppInstalled(data.get("installedAppData").toString()));
                    }
                    else if (methodName.equalsIgnoreCase("launchApp"))
                    {
                        driver.launchApp();
                        result="true";
                    }
                    else if (methodName.equalsIgnoreCase("closeApp"))
                    {
                        driver.closeApp();
                        result="true";
                    }
                    else if(methodName.equalsIgnoreCase("removeApp")){
                        driver.removeApp(data.get("removeAppData").toString());
                        result="true";
                    }
                    else if(methodName.equalsIgnoreCase("activateApp")){
                        driver.activateApp(data.get("bundleIdData").toString());
                        result="true";
                    }
                    else if(methodName.equalsIgnoreCase("terminateApp")){
                        driver.terminateApp(data.get("bundleIdData").toString());
                        result="true";
                    }
                    else if(methodName.equalsIgnoreCase("resetApp")){
                        driver.resetApp();
                        result="true";
                    }
                    else if(methodName.equalsIgnoreCase("queryAppState")){
                        driver.queryAppState(data.get("bundleIdData").toString());
                        result="true";
                    }
                    else if(methodName.equalsIgnoreCase("runAppInBackground")){
                        driver.runAppInBackground(Duration.ofSeconds(Integer.valueOf(data.get("timeDate").toString())));
                        result="true";
                    }


                    else {
                        throw new MethodNotFoundException("Method does not exist");
                    }

                } else {
                    throw new BadRequestException("Not Parameter present in the data set");
                }

                return result;
            }

            private MobileElement findElement(String by, String value) throws ElementNotFoundException {

                MobileElement element = null;

               try {
                   if (by.equalsIgnoreCase("accessibilityId")) {
                       element = (MobileElement) driver.findElementByAccessibilityId(value);
                   }
                   else if (by.equalsIgnoreCase("Id")){
                       element = (MobileElement) driver.findElementById(value);
                   }
                   else if (by.equalsIgnoreCase("Name")){
                       element = (MobileElement) driver.findElementByName(value);
                   }
                   else if (by.equalsIgnoreCase("XPath")){
                       element = (MobileElement) driver.findElementByXPath(value);
                   }
                   else if (by.equalsIgnoreCase("ClassName")){
                       element = (MobileElement) driver.findElementByClassName(value);
                   }
                   else{
                       throw new BadRequestException("Not Parameter present in the data set");
                   }
               } catch(Exception ex){

                   throw new ElementNotFoundException(ex.getMessage());
               }

                return element;
            }

        }
