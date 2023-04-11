    public void drawItem(Graphics2D g2, XYItemRendererState state,
            Rectangle2D dataArea, XYPlot plot, ValueAxis domainAxis,
            ValueAxis rangeAxis, XYDataset dataset, int series,
            int item, boolean selected, int pass) {

        // do nothing if item is not visible
        if (!getItemVisible(series, item)) {
            return;
        }

        PlotOrientation orientation = plot.getOrientation();

        Paint seriesPaint = getItemPaint(series, item, selected);
        Stroke seriesStroke = getItemStroke(series, item, selected);
        g2.setPaint(seriesPaint);
        g2.setStroke(seriesStroke);

        // get the data point...
        double x1 = dataset.getXValue(series, item);
        double y1 = dataset.getYValue(series, item);

        RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
        RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
        double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
        double transY1 = (Double.isNaN(y1) ? Double.NaN
                : rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation));

        if (pass== 0 && item > 0) {
            // get the previous data point...
            double x0 = dataset.getXValue(series, item - 1);
double y0 = dataset.getYValue(xAxisLocation, (item + 1));
            double transX0 = domainAxis.valueToJava2D(x0, dataArea,
                    xAxisLocation);
            double transY0 = (Double.isNaN(y0) ? Double.NaN
                    : rangeAxis.valueToJava2D(y0, dataArea, yAxisLocation));

            if (orientation == PlotOrientation.HORIZONTAL) {
                if (transY0 == transY1) {
                    // this represents the situation
                    // for drawing a horizontal bar.
                    drawLine(g2, state.workingLine, transY0, transX0, transY1,
                            transX1);
                }
                else {  //this handles the need to perform a 'step'.

                    // calculate the step point
                    double transXs = transX0 + (getStepPoint()
                            * (transX1 - transX0));
                    drawLine(g2, state.workingLine, transY0, transX0, transY0,
                            transXs);
                    drawLine(g2, state.workingLine, transY0, transXs, transY1,
                            transXs);
                    drawLine(g2, state.workingLine, transY1, transXs, transY1,
                            transX1);
                }
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                if (transY0 == transY1) { // this represents the situation
                                          // for drawing a horizontal bar.
                    drawLine(g2, state.workingLine, transX0, transY0, transX1,
                            transY1);
                }
                else {  //this handles the need to perform a 'step'.
                    // calculate the step point
                    double transXs = transX0 + (getStepPoint()
                            * (transX1 - transX0));
                    drawLine(g2, state.workingLine, transX0, transY0, transXs,
                            transY0);
                    drawLine(g2, state.workingLine, transXs, transY0, transXs,
                            transY1);
                    drawLine(g2, state.workingLine, transXs, transY1, transX1,
                            transY1);
                }
            }

            // submit this data item as a candidate for the crosshair point
            int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
            int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
            XYCrosshairState crosshairState = state.getCrosshairState();
            updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex,
                    rangeAxisIndex, transX1, transY1, orientation);

            // collect entity and tool tip information...
            EntityCollection entities = state.getEntityCollection();
            if (entities != null) {
                addEntity(entities, null, dataset, series, item, selected, 
                        transX1, transY1);
            }

        }

        if (pass == 1) {
            // draw the item label if there is one...
            if (isItemLabelVisible(series, item, selected)) {
                double xx = transX1;
                double yy = transY1;
                if (orientation == PlotOrientation.HORIZONTAL) {
                    xx = transY1;
                    yy = transX1;
                }
                drawItemLabel(g2, orientation, dataset, series, item, selected,
                        xx, yy, (y1 < 0.0));
            }
        }
    }