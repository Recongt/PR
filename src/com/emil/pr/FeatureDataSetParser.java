package com.emil.pr;

import java.util.ArrayList;
import java.util.List;

public class FeatureDataSetParser {
    private String content;
    private FeatureInputData data;
    
    public FeatureDataSetParser(String content) {
        this.content = content;
        this.data = new FeatureInputData();
    }
    
    public FeatureInputData parse() throws Exception {
        getDatasetParameters();
        fillFeatureMatrix();
        
        return this.data;
    }
    
    private void getDatasetParameters() throws Exception {
        String stmp=content, saux="";
        // analyze the first line and get feature count: assume that number of features
        // equals number of commas
        saux = content.substring(content.indexOf(',')+1, content.indexOf('$'));
        if(saux.length()==0) throw new Exception("The first line is empty");
        // saux stores the first line beginning from the first comma
        int count=0;
        while(saux.indexOf(',') >0){
            saux = saux.substring(saux.indexOf(',')+1);            
            count++;
        }
        data.setFeatureCount(count+1); // the first parameter
        // Determine number of classes, class names and number of samples per class
        boolean New;
        int index=-1;
        List<String> nameList = new ArrayList<String>();
        List<Integer> countList = new ArrayList<Integer>();
        List<Integer> labelList = new ArrayList<Integer>();
        while(stmp.length()>1){
            saux = stmp.substring(0,stmp.indexOf(' '));
            New = true; 
            index++; // new class index
            for(int i=0; i<nameList.size();i++) 
                if(saux.equals(nameList.get(i))) {
                    New=false;
                    index = i; // class index
                }
            if(New) {
                nameList.add(saux);
                countList.add(0);
            }
            else{
                countList.set(index, countList.get(index)+1);
            }           
            labelList.add(index); // class index for current row
            stmp = stmp.substring(stmp.indexOf('$')+1);
        }
        // based on results of the above analysis, create variables
        
        String[] classNames;
        classNames = new String[nameList.size()];
        for(int i=0; i<classNames.length; i++)
            classNames[i]=nameList.get(i);
        this.data.setClassNames(classNames);
        
        int[] sampleCount;
        sampleCount = new int[countList.size()];
        for(int i=0; i<sampleCount.length; i++)
            sampleCount[i] = countList.get(i)+1;
        this.data.setSampleCount(sampleCount);
        
        int[] classLabels;
        classLabels = new int[labelList.size()];
        for(int i=0; i<classLabels.length; i++)
            classLabels[i] = labelList.get(i);
        this.data.setClassLabels(classLabels);
    }

    private void fillFeatureMatrix() throws Exception {
        // having determined array size and class labels, fills in the feature matrix
        int n = 0;
        String saux, stmp = this.content;
        
        int[] sampleCount = this.data.getSampleCount();
        for(int i=0; i<sampleCount.length; i++)
            n += sampleCount[i];
        if(n<=0) throw new Exception("no samples found");
        F = new double[FeatureCount][n]; // samples are placed column-wise
        for(int j=0; j<n; j++){
            saux = stmp.substring(0,stmp.indexOf('$'));
            saux = saux.substring(stmp.indexOf(',')+1);
            for(int i=0; i<FeatureCount-1; i++) {
                F[i][j] = Double.parseDouble(saux.substring(0,saux.indexOf(',')));
                saux = saux.substring(saux.indexOf(',')+1);
            }
            F[FeatureCount-1][j] = Double.parseDouble(saux);
            stmp = stmp.substring(stmp.indexOf('$')+1);
        }
        int cc = 1;
    }
    
}
