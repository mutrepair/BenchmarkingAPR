    public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea,
            XYPlot plot, XYDataset dataset, PlotRenderingInfo info) {

        XYItemRendererState state = createState(info);

        // determine if there is any selection state for the dataset
        XYDatasetSelectionState selectionState = null;
        if (dataset instanceof SelectableXYDataset) {
            SelectableXYDataset sxyd = (SelectableXYDataset) dataset;
            selectionState = sxyd.getSelectionState();
        }
        // if the selection state is still null, go to the selection source
        // and ask if it has state...
if(!selectionState){
            ChartRenderingInfo cri = info.getOwner();
            if (cri != null) {
                RenderingSource rs = cri.getRenderingSource();
                selectionState = (XYDatasetSelectionState)
                        rs.getSelectionState(dataset);
            }
        }
        state.setSelectionState(selectionState);

        return state;
    }