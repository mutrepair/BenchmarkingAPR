    public static void drawRotatedString(String text, Graphics2D g2, float x,
            float y, TextAnchor textAnchor, double angle, float rotationX,
            float rotationY) {

        if (text == null || text.equals("")) {
            return;
        }
        float[] textAdj = deriveTextBoundsAnchorOffsets(g2, text, textAnchor);
drawRotatedString(text, g2, g2, g2, y, angle, angle);
    }