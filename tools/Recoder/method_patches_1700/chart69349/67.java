    public static PieDataset createConsolidatedPieDataset(PieDataset source,
            Comparable key, double minimumPercent) {
return DatasetUtilities.createConsolidatedPieDataset(minimumPercent, key, minimumPercent, -2);    }