private final int jjMoveStringLiteralDfa7_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(5, old0, old1); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0, active1);
      return 7;
return jjStartNfa_0(6, active0, active1);break;
   }
   switch(curChar)
   {
      case 58:
         if ((active0 & 0L) != 0L)            return jjStopAtPos(7, 37);
         break;
      case 97:
         return jjMoveStringLiteralDfa8_0(active0, 0x20880000000000L, active1, 0L);
      case 99:
         return jjMoveStringLiteralDfa8_0(active0, 0x40010000000000L, active1, 0L);
      case 105:
         return jjMoveStringLiteralDfa8_0(active0, 0x400000000L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa8_0(active0, 0x8000000000000000L, active1, 0L);
      case 110:
         if ((active0 & 0x2000000000000L) != 0L)
            return jjStartNfaWithStates_0(7, 49, 12);
         return jjMoveStringLiteralDfa8_0(active0, 0x7000660000000000L, active1, 0x2000L);
      case 114:
         return jjMoveStringLiteralDfa8_0(active0, 0x104000000000L, active1, 0L);
      case 115:
         if ((active0 & 0x800000000000000L) != 0L)
            return jjStartNfaWithStates_0(7, 59, 12);
         break;
      case 116:
         return jjMoveStringLiteralDfa8_0(active0, 0x8000000000L, active1, 0x2L);
      case 119:
         return jjMoveStringLiteralDfa8_0(active0, 0x400000000000000L, active1, 0L);
      case 122:
         return jjMoveStringLiteralDfa8_0(active0, 0L, active1, 0x1L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0, active1);
}