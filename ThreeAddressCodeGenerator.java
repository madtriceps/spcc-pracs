import java.util.Scanner;

public class ThreeAddressCodeGenerator {
    private static int tempCount = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Generate three-address code for:");
            System.out.println("1) Assignment");
            System.out.println("2) Conditional");
            System.out.println("3) Indexed Assignment");
            System.out.println("4) Copy");
            System.out.println("5) Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            System.out.println();

            switch (choice) {
                case 1:
                    generateAssignmentCode();
                    break;
                case 2:
                    generateConditionalCode();
                    break;
                case 3:
                    generateIndexedAssignmentCode();
                    break;
                case 4:
                    generateCopyCode();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }

            System.out.println();
        }
    }

    private static void generateAssignmentCode() {
        String expression = "a = b * c + d";
        System.out.println("Expression: " + expression);

        String[] parts = expression.split(" = ");
        String lhs = parts[0].trim();
        String rhs = parts[1].trim();
        String[] terms = rhs.split("\\s+");
        String result = lhs;
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < terms.length; i++) {
            if (terms[i].equals("+") || terms[i].equals("*")) {
                String op = terms[i];
                String operand1 = terms[i - 1];
                String operand2 = terms[i + 1];
                String temp = "t" + tempCount++;
                code.append(temp).append(" = ").append(operand1).append(" ").append(op).append(" ").append(operand2).append(";\n");
                terms[i + 1] = temp;
            }
        }

        code.append(result).append(" = ").append(terms[terms.length - 1]).append(";");
        System.out.println("Three-address code:");
        System.out.println(code.toString());
    }

    private static void generateConditionalCode() {
        String expression = "if (a < b) then c = d + e else c = d - e";
        System.out.println("Expression: " + expression);

        String[] parts = expression.split(" then ");
        String condition = parts[0].substring(3).trim();
        String thenPart = parts[1].split(" else ")[0].trim();
        String elsePart = parts[1].split(" else ")[1].trim();
        String trueLabel = "L" + tempCount++;
        String endLabel = "L" + tempCount++;
        StringBuilder code = new StringBuilder();

        code.append("if ").append(condition).append(" goto ").append(trueLabel).append(";\n");
        code.append(elsePart).append(" goto ").append(endLabel).append(";\n");
        code.append(trueLabel).append(": ").append(thenPart).append(" goto ").append(endLabel).append(";\n");
        code.append(endLabel).append(":");
        System.out.println("Three-address code:");
        System.out.println(code.toString());
    }

    private static void generateCopyCode() {
        String expression = "x = y";
        System.out.println("Expression: " + expression);

        String[] parts = expression.split(" = ");
        String target = parts[0].trim();
        String source = parts[1].trim();
        StringBuilder code = new StringBuilder();

        code.append(target).append(" = ").append(source).append(";");
        System.out.println("Three-address code:");
        System.out.println(code.toString());
    }

    private static void generateIndexedAssignmentCode() {
        String expression = "x[2] = y + 5";
        System.out.println("Expression: " + expression);

        String[] parts = expression.split(" = ");
        String target = parts[0].trim();
        String operation = parts[1].trim();
        int startIndex = target.indexOf('[');
        int endIndex = target.indexOf(']');
        String arrayName = target.substring(0, startIndex).trim();
        String index = target.substring(startIndex + 1, endIndex).trim();
        String[] operationParts = operation.split("\\s+");
        String operand1 = operationParts[0];
        String operator = operationParts[1];
        String operand2 = operationParts[2];
        String intermediateVar = "T" + tempCount++;
        StringBuilder code = new StringBuilder();

        code.append(intermediateVar).append(" = ").append(operand1).append(" ").append(operator).append(" ").append(operand2).append(";\n");
        code.append(arrayName).append("[").append(index).append("] = ").append(intermediateVar).append(";");
        System.out.println("Three-address code:");
        System.out.println(code.toString());
    }
}
