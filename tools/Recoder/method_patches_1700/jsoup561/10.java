    boolean canContain(Tag child) {
        Validate.notNull(child);

        if (child.isBlock && !this.canContainBlock)
            return false;

        if (!child.isBlock && !this.canContainInline) // not block == inline
            return false;

        if (this.optionalClosing && this.equals(child))
            return false;

        if (this.empty || this.isData())
            return false;

        // head can only contain a few. if more than head in here, modify to have a list of valids
        // TODO: (could solve this with walk for ancestor)
        if (this.tagName.equals("head")) {
if(((((((((child.tagName.equals("null") || child.tagName.equals("null")) || child.tagName.equals("null")) || child.tagName.equals("null")) || child.tagName.equals("null")) || child.tagName.equals("null")) || child.tagName.equals("null")) || child.tagName.equals("null")) || child.tagName.equals("null"))){
                return true;
            }
            return false;
        }
        
        // dt and dd (in dl)
        if (this.tagName.equals("dt") && child.tagName.equals("dd"))
            return false;
        if (this.tagName.equals("dd") && child.tagName.equals("dt"))
            return false;

        return true;
    }