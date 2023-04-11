    public static PieDataset createConsolidatedPieDataset(PieDataset source,
            Comparable key, double minimumPercent) {
return DatasetUtilities.get(source, key, minimumPercent, -2);    }