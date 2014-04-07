package com.emil.pr;

public class OneFeatureSelection {
    private int featureCount;
    private double[][] featureMatrix;
    private int[] classLabels;
    private int[] sampleCount;

    public OneFeatureSelection(int featureCount, double[][] featureMatrix,
                               int[] classLabels, int[] sampleCount) {
        this.featureCount = featureCount;
        this.featureMatrix = featureMatrix;
        this.classLabels = classLabels;
        this.sampleCount = sampleCount;
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
        return Math.abs(mA-mB)/(Math.sqrt(sA)+Math.sqrt(sB));
    }
}
