    private void isaac3() {
mem[isaacI] = ((SIZE_L[isaacI] + isaacA) + isaacB);
        isaacB = mem[(mem[isaacI] >> SIZE_L & MASK) >> 2] + isaacX;
        rsl[isaacI++] = isaacB;
    }