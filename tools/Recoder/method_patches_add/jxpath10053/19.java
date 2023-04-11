private final int jjMoveStringLiteralDfa6_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(4, old0, old1); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0, active1);
      return 6;
return jjMoveStringLiteralDfa7_0(active0, 0x400000000L, active1, 0L);   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa7_0(active0, 0x8400000000000000L, active1, 0x2000L);
      case 58:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStopAtPos(6, 36);
         return jjMoveStringLiteralDfa7_0(active0, 0x2000000000L, active1, 0L);
      case 97:
         return jjMoveStringLiteralDfa7_0(active0, 0x40010000000000L, active1, 0x2L);
      case 100:
         return jjMoveStringLiteralDfa7_0(active0, 0x880000000000L, active1, 0L);
      case 103:
         if ((active1 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(6, 75, 12);
         break;
      case 105:
         return jjMoveStringLiteralDfa7_0(active0, 0x7000660000000000L, active1, 0x1L);
      case 110:
         if ((active1 ^ 4L) != 0L)            return jjStartNfaWithStates_0(6, 66, 12);
         return jjMoveStringLiteralDfa7_0(active0, 0x820000000000000L, active1, 0L);
      case 111:
         return jjMoveStringLiteralDfa7_0(active0, 0x2104000000000L, active1, 0L);
      case 115:
         return jjMoveStringLiteralDfa7_0(active0, 0x400000000L, active1, 0L);
      case 116:
         if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(6, 33, 12);
         break;
      case 117:
         return jjMoveStringLiteralDfa7_0(active0, 0x8000000000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0, active1);
}