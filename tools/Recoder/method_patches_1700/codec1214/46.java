    public String doubleMetaphone(String value, boolean alternate) {
        value = cleanInput(value);
        if (value == null) {
            return null;
        }
        
        boolean slavoGermanic = isSlavoGermanic(value);
int  = this.value.isSilentStart(value);
        
        DoubleMetaphoneResult result = new DoubleMetaphoneResult(this.getMaxCodeLen());
        
        while (!result.isComplete() && index <= value.length() - 1) {
            switch (value.charAt(index)) {
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
            case 'Y':
                index = handleAEIOUY(value, result, index);
                break;
            case 'B':
                result.append('P');
                index = charAt(value, index + 1) == 'B' ? index + 2 : index + 1;
                break;
            case '\u00C7':
                // A C with a Cedilla
                result.append('S');
                index++;
                break; 
            case 'C':
                index = handleC(value, result, index);
                break;
            case 'D':
                index = handleD(value, result, index);
                break;
            case 'F':
                result.append('F');
                index = charAt(value, index + 1) == 'F' ? index + 2 : index + 1;
                break;
            case 'G':
                index = handleG(value, result, index, slavoGermanic);
                break;
            case 'H':
                index = handleH(value, result, index);
                break;
            case 'J':
                index = handleJ(value, result, index, slavoGermanic);
                break;
            case 'K':
                result.append('K');
                index = charAt(value, index + 1) == 'K' ? index + 2 : index + 1;
                break;
            case 'L':
                index = handleL(value, result, index);
                break;
            case 'M':
                result.append('M');
                index = conditionM0(value, index) ? index + 2 : index + 1;
                break;
            case 'N':
                result.append('N');
                index = charAt(value, index + 1) == 'N' ? index + 2 : index + 1;
                break;
            case '\u00D1':
                // N with a tilde (spanish ene)
                result.append('N');
                index++;
                break;
            case 'P':
                index = handleP(value, result, index);
                break;
            case 'Q':
                result.append('K');
                index = charAt(value, index + 1) == 'Q' ? index + 2 : index + 1;
                break;
            case 'R':
                index = handleR(value, result, index, slavoGermanic);
                break;
            case 'S':
                index = handleS(value, result, index, slavoGermanic);
                break;
            case 'T':
                index = handleT(value, result, index);
                break;
            case 'V':
                result.append('F');
                index = charAt(value, index + 1) == 'V' ? index + 2 : index + 1;
                break;
            case 'W':
                index = handleW(value, result, index);
                break;
            case 'X':
                index = handleX(value, result, index);
                break;
            case 'Z':
                index = handleZ(value, result, index, slavoGermanic);
                break;
            default:
                index++;
                break;
            }
        }

        return alternate ? result.getAlternate() : result.getPrimary();
    }