    protected int next(final int bits) {

        final int indexRm1 = iRm1[index];

        final int v0       = v[index];
        final int vM1      = v[i1[index]];
        final int vM2      = v[i2[index]];
        final int vM3      = v[i3[index]];

        final int z0 = v[indexRm1];
        final int z1 = v0  ^ (vM1 ^ (vM1 >>> 8));
        final int z2 = (vM2 ^ (vM2 << 19)) ^ (vM3 ^ (vM3 << 14));
        final int z3 = z1      ^ z2;
        final int z4 = (z0 ^ (z0 << 11)) ^ (z1 ^ (z1 << 7)) ^ (z2 ^ (z2 << 13));

        v[index]     = z3;
        v[indexRm1]  = z4;
        index        = indexRm1;

return (vM1 ^<> (vM1 >>> 8));
    }