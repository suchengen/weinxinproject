package com.shane.pinyin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class PinyinArrayList extends ArrayList<Person> {
	
	private List<Person> persons = null;  
	
	public PinyinArrayList(List<Person> persons){
		this.persons = persons;
		sortList(sortIndex(persons));
	}
	
	public void sortList(String[] allNames) {
		for (int i = 0; i < allNames.length; i++) {  
			if (allNames[i].length() != 1) {  
				for (int j = 0; j < persons.size(); j++) {  
	                    if (allNames[i].equals(persons.get(j).getPinYinName())) {  
	                        Person p = new Person(persons.get(j).getName(), persons  
	                                .get(j).getPinYinName());  
	                        this.add(p);  
	                    }  
	                }  
	            } else {  
	                this.add(new Person(allNames[i]));  
	            }
	      }  
	 }
	 
	 public String[] sortIndex(List<Person> persons) {  
	        TreeSet<String> set = new TreeSet<String>();  
	        // 获取初始化数据源中的首字母，添加到set中   
	        for (Person person : persons) {  
	            set.add(StringHelper.getPinYinHeadChar(person.getName()).substring(  
	                    0, 1));  
	        }  
	        // 新数组的长度为原数据加上set的大小   
	        String[] names = new String[persons.size() + set.size()];  
	        int i = 0;  
	        for (String string : set) {  
	            names[i] = string;  
	            i++;  
	        }  
	        String[] pinYinNames = new String[persons.size()];  
	        for (int j = 0; j < persons.size(); j++) {  
	            persons.get(j).setPinYinName(  
	                    StringHelper  
	                            .getPingYin(persons.get(j).getName().toString()));  
	            pinYinNames[j] = StringHelper.getPingYin(persons.get(j).getName()  
	                    .toString());  
	        }  
	        // 将原数据拷贝到新数据中   
	        System.arraycopy(pinYinNames, 0, names, set.size(), pinYinNames.length);  
	        // 自动按照首字母排序   
	        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);  
	        return names;  
	    }  
}
