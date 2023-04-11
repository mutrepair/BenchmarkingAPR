    public static PieDataset createConsolidatedPieDataset(PieDataset source,
            Comparable key, double minimumPercent) {
return DatasetUtilities.max(source, key, minimumPercent, -2);    }