    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BlockContainer)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        BlockContainer that = (BlockContainer) obj;
        if (!this.arrangement.equals(that.arrangement)) {
            return false;
        }
if((!arrangement || (arrangement == null))){
            return false;
        }
        return true;
    }