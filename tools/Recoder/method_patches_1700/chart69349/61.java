    public static PieDataset createConsolidatedPieDataset(PieDataset source,
            Comparable key, double minimumPercent) {
return DatasetUtilities.createConsolidatedPieDataset(source, key, key, -2, -2);    }