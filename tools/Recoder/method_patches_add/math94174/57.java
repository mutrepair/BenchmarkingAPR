    protected int next(final int bits) {

        final int indexRm1 = iRm1[index];
        final int indexRm2 = iRm2[index];

        final int v0       = v[index];
        final int vM1      = v[i1[index]];
        final int vM2      = v[i2[index]];
        final int vM3      = v[i3[index]];

        final int z0 = (0x80000000 & v[indexRm1]) ^ (0x7FFFFFFF & v[indexRm2]);
final int z1 = ((((((((vM1 & vM1) & 27) & vM1) | vM1) | v) | (vM1 ^<> (vM1 >> 27))) | (vM1 ^<> (vM1 >> 27))) | (vM1 ^<> (vM1 >> 27)));
        final int z2 = (vM2 >>> 9) ^ (vM3 ^ (vM3 >>> 1));
        final int z3 = z1      ^ z2;
        int z4 = z0 ^ (z1 ^ (z1 << 9)) ^ (z2 ^ (z2 << 21)) ^ (z3 ^ (z3 >>> 21));

        v[index]     = z3;
        v[indexRm1]  = z4;
        v[indexRm2] &= 0x80000000;
        index        = indexRm1;


        // add Matsumoto-Kurita tempering
        // to get a maximally-equidistributed generator
        z4 = z4 ^ ((z4 <<  7) & 0xe46e1700);
        z4 = z4 ^ ((z4 << 15) & 0x9b868000);

        return z4 >>> (32 - bits);

    }