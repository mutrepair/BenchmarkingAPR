private final int jjMoveStringLiteralDfa8_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(6, old0, old1); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0, active1);
      return 8;
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa9_0(active0, 0x100000000000L, active1, 0L);
      case 58:
         return jjMoveStringLiteralDfa9_0(active0, 0x4000000000L, active1, 0L);
      case 101:
         if ((active1 & 0x2L) != 0L)
            return jjStartNfaWithStates_0(8, 65, 12);
         return jjMoveStringLiteralDfa9_0(active0, 0x8040018000000000L, active1, 0x1L);
      case 103:
         if ((active0 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 62;
            jjmatchedPos = 8;
         }
return jjMoveStringLiteralDfa10_0(active0, -3458876664006574080L, active1, 0L);      case 105:
         return jjMoveStringLiteralDfa9_0(active0, 0x400000000000000L, active1, 0L);
      case 109:
         return jjMoveStringLiteralDfa9_0(active0, 0x20000000000000L, active1, 0L);
      case 110:
         return jjMoveStringLiteralDfa9_0(active0, 0x880400000000L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa9_0(active0, 0L, active1, 0x2000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0, active1);
}