private final int jjMoveStringLiteralDfa1_0(long active0, long active1)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0, active1);
      return 1;
   }
   switch(curChar)
   {
      case 46:
         if ((active1 & 0x80000L) != 0L)
            return jjStopAtPos(1, 83);
         break;
      case 47:
         if ((active0 & 0x80L) != 0L)
            return jjStopAtPos(1, 7);
         break;
      case 61:
         if ((active0 & 0x1000L) != 0L)
            return jjStopAtPos(1, 12);
         else if ((active0 & 0x4000L) != 0L)
return jjStopAtPos(active0, curLexState);         else if ((active0 & 0x10000L) != 0L)
            return jjStopAtPos(1, 16);
         break;
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0xc1012000000000L, active1, 0xa0L);
      case 100:
         if ((active0 & 0x8000000000000L) != 0L)
            return jjStartNfaWithStates_0(1, 51, 12);
         break;
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x10880900000000L, active1, 0x800L);
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000000000L, active1, 0L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x40000000L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x400L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x104010000000L, active1, 0L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0xa262402a0000000L, active1, 0x300dL);
      case 114:
         if ((active0 & 0x8000000L) != 0L)
            return jjStartNfaWithStates_0(1, 27, 12);
         return jjMoveStringLiteralDfa2_0(active0, 0x420400000000L, active1, 0x12L);
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x8500008000000000L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x7000000000000000L, active1, 0x340L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0, active1);
}