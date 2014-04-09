package com.emil.pr;

import com.emil.pr.FeatureDataSetParser;
import com.emil.pr.FeatureInputData;
import com.emil.pr.FeatureMatrixFiller;
import com.emil.pr.FeatureSelectionResult;
import com.emil.pr.OneFeatureSelection;
import org.junit.Assert;
import org.junit.Test;

public class OneFeatureSelectionTest {
    
    @Test
    public void Test1() throws Exception {
        String content
          = "A a1,0,0$" +
            "A a2,2,2$" +
            "A a3,4,2$" +
            "B b1,2,0$" +
            "B b2,1,1$" +
            "B b3,3,2$";
        
        FeatureDataSetParser parser = new FeatureDataSetParser(content);
        FeatureInputData data = parser.parse();
        FeatureMatrixFiller filler = new FeatureMatrixFiller();
        filler.fill(content, data);
        
        OneFeatureSelection selection = new OneFeatureSelection(data);
        FeatureSelectionResult result = selection.selectFeatures();
        
        Assert.assertEquals(1, result.getSelectedFeatureIndex());
        Assert.assertEquals(0.1894, result.getFisherRatio(), 0.0001);
    }
    
}
