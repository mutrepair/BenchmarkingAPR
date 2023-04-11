    public static PieDataset createConsolidatedPieDataset(PieDataset source,
            Comparable key, double minimumPercent) {
if((key == null)){
return null;}        return DatasetUtilities.createConsolidatedPieDataset(source, key,
                minimumPercent, -2);    }