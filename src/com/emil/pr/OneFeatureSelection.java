package com.emil.pr;

public class OneFeatureSelection {
    private final int featureCount;
    private final double[][] featureMatrix;
    private final int[] classLabels;
    private final int[] sampleCount;

    public OneFeatureSelection(FeatureInputData data) {
        this.featureCount = data.getFeatureCount();
        this.featureMatrix = data.getF();
        this.classLabels = data.getClassLabels();
        this.sampleCount = data.getSampleCount();
    }
    
    public FeatureSelectionResult selectFeatures() {
        FeatureSelectionResult result = new FeatureSelectionResult();
        // for now: check all individual features using 1D, 2-class Fisher criterion

        double FLD=0, tmp;
        int max_ind=-1;        
        for(int i=0; i<featureCount; i++){
            if((tmp=computeFisherLD(featureMatrix[i]))>FLD){
                FLD=tmp;
                max_ind = i;
            }
        }
        // to do: compute for higher dimensional spaces,
        // use e.g. SFS for candidate selection
        
        result.setSelectedFeatureIndex(max_ind);
        result.setFisherRatio(FLD);
        return result;
    }
    
    private double computeFisherLD(double[] vec) {
        // 1D, 2-classes
        double mA=0, mB=0, sA=0, sB=0;
        for(int i=0; i<vec.length; i++){
            //która klasa, są dwie klasy
            if(classLabels[i]==0) {
                mA += vec[i];
                sA += vec[i]*vec[i];
            }
            else {
                mB += vec[i];
                sB += vec[i]*vec[i];
            }
        }
        mA /= sampleCount[0];
        mB /= sampleCount[1];
        sA = sA/sampleCount[0] - mA*mA;
        sB = sB/sampleCount[1] - mB*mB;
        
        double result = Math.abs(mA-mB)/(Math.sqrt(sA)+Math.sqrt(sB));
        return result;
    }
}
