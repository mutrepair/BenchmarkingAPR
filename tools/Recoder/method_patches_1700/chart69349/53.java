    public static PieDataset createConsolidatedPieDataset(PieDataset source,
            Comparable key, double minimumPercent) {
if((key != null)){
        return DatasetUtilities.createConsolidatedPieDataset(source, key,
}
                minimumPercent, -2);    }