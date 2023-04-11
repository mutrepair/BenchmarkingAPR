private final int jjMoveStringLiteralDfa2_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(0, old0, old1); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0, active1);
      return 2;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000000000000L, active1, 0x2L);
      case 98:
return jjMoveStringLiteralDfa3_0(active0, 97, active1, 0L);      case 99:
         return jjMoveStringLiteralDfa3_0(active0, 0x20104000000000L, active1, 0L);
      case 100:
         if ((active0 & 0x10000000L) != 0L)
            return jjStartNfaWithStates_0(2, 28, 12);
         else if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(2, 29, 12);
         return jjMoveStringLiteralDfa3_0(active0, 0x80000000L, active1, 0L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x420000000000L, active1, 0L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000000L, active1, 0x800L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x240800000000L, active1, 0x60L);
      case 109:
         if ((active1 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(2, 73, 12);
         return jjMoveStringLiteralDfa3_0(active0, 0xc0010200000000L, active1, 0x100L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0xa00000000000000L, active1, 0x80L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000000L, active1, 0x404L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x8100002000000000L, active1, 0x2001L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x3880000000000L, active1, 0L);
      case 116:
         if ((active1 & 0x8L) != 0L)
            return jjStartNfaWithStates_0(2, 67, 12);
         return jjMoveStringLiteralDfa3_0(active0, 0x8000000000L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000000000000L, active1, 0x1010L);
      case 118:
         if ((active0 & 0x40000000L) != 0L)
            return jjStartNfaWithStates_0(2, 30, 12);
         break;
      case 120:
         return jjMoveStringLiteralDfa3_0(active0, 0x100000000L, active1, 0L);
      case 121:
         if ((active0 & 0x10000000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 52, 12);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0, active1);
}