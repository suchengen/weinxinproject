package com.shane.pinyin;
public class Person {  
    private String name;  
    private String pinYinName;
    private boolean isAdd;
  
    public Person(String name) {  
        super();  
        this.name = name;
        isAdd = false;
    }  
  
    public Person(String name, String pinYinName) {  
        super();  
        this.name = name;  
        this.pinYinName = pinYinName;
        isAdd = false;
    }
    
    public boolean getIsAdd(){
    	return isAdd;
    }
    
    public void setIsAdd(boolean isAdd){
    	this.isAdd = isAdd;
    }
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getPinYinName() {  
        return pinYinName;  
    }  
  
    public void setPinYinName(String pinYinName) {  
        this.pinYinName = pinYinName;  
    }  
  
}  

