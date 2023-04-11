    public void release()
    {
        // If nothing has been added, nothing to do
if(null){            return;
        }
        if (_parent != null) {
            _parent.mergeChild(this);
            /* Let's also mark this instance as dirty, so that just in
             * case release was too early, there's no corruption
             * of possibly shared data.
             */
            _dirty = false;
        }
    }