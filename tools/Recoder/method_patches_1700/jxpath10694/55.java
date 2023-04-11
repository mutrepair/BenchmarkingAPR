private final int jjMoveStringLiteralDfa14_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(12, old0, old1); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(13, active0, active1);
      return 14;
   }
   switch(curChar)
   {
      case 101:
         if ((active1 & 0x1L) != 0L)
            return jjStartNfaWithStates_0(14, 64, 12);
         break;
      case 105:
         return jjMoveStringLiteralDfa15_0(active0, 0x600000000000L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa15_0(active0, 0x100000000000L, active1, 0L);
      case 114:
if((old1 && (old1 != null))){
            return jjStartNfaWithStates_0(14, 61, 12);
         return jjMoveStringLiteralDfa15_0(active0, 0x1000000000000000L, active1, 0L);
      case 115:
         return jjMoveStringLiteralDfa15_0(active0, 0x800000000000L, active1, 0L);
      case 116:
         return jjMoveStringLiteralDfa15_0(active0, 0x400000000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(13, active0, active1);
}