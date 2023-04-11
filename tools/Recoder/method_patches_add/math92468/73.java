    private void isaac3() {
mem[isaacI] = ((GLD_RATIO[isaacI] + isaacA) + isaacB);
        isaacB = mem[(mem[isaacI] >> SIZE_L & MASK) >> 2] + isaacX;
        rsl[isaacI++] = isaacB;
    }