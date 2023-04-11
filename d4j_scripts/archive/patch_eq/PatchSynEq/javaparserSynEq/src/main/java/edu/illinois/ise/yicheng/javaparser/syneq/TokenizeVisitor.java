package edu.illinois.ise.yicheng.javaparser.syneq;

import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.CompactConstructorDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.ReceiverParameter;
import com.github.javaparser.ast.body.RecordDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.ast.expr.ArrayCreationExpr;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.CharLiteralExpr;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.PatternExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.SuperExpr;
import com.github.javaparser.ast.expr.SwitchExpr;
import com.github.javaparser.ast.expr.TextBlockLiteralExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.expr.TypeExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.modules.ModuleDeclaration;
import com.github.javaparser.ast.modules.ModuleExportsDirective;
import com.github.javaparser.ast.modules.ModuleOpensDirective;
import com.github.javaparser.ast.modules.ModuleProvidesDirective;
import com.github.javaparser.ast.modules.ModuleRequiresDirective;
import com.github.javaparser.ast.modules.ModuleUsesDirective;
import com.github.javaparser.ast.stmt.AssertStmt;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ContinueStmt;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ExplicitConstructorInvocationStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.LabeledStmt;
import com.github.javaparser.ast.stmt.LocalClassDeclarationStmt;
import com.github.javaparser.ast.stmt.LocalRecordDeclarationStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.UnparsableStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.stmt.YieldStmt;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.IntersectionType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.type.UnionType;
import com.github.javaparser.ast.type.UnknownType;
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.type.WildcardType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * @author Yicheng Ouyang
 * @Date 1/31/23
 */

public class TokenizeVisitor extends VoidVisitorAdapter {

    public TokenizeVisitor() {
        super();
    }

    @Override
    public void visit(AnnotationDeclaration n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(AnnotationMemberDeclaration n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ArrayAccessExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ArrayCreationExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ArrayInitializerExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(AssertStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(AssignExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(BinaryExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(BlockStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(BooleanLiteralExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(BreakStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(CastExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(CatchClause n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(CharLiteralExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ClassExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceType n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(CompilationUnit n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ConditionalExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ConstructorDeclaration n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ContinueStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(DoStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(DoubleLiteralExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(EmptyStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(EnclosedExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(EnumConstantDeclaration n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(EnumDeclaration n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ExplicitConstructorInvocationStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ExpressionStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(FieldAccessExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(FieldDeclaration n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ForEachStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ForStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(IfStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(InitializerDeclaration n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(InstanceOfExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(IntegerLiteralExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(LabeledStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(LongLiteralExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(MarkerAnnotationExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(MemberValuePair n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(MethodCallExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(MethodDeclaration n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(NameExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(NormalAnnotationExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(NullLiteralExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ObjectCreationExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(PackageDeclaration n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(Parameter n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(PrimitiveType n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(Name n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(SimpleName n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ArrayType n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ArrayCreationLevel n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(IntersectionType n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(UnionType n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ReturnStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(SingleMemberAnnotationExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(StringLiteralExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(SuperExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(SwitchEntry n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(SwitchStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(SynchronizedStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ThisExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ThrowStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(TryStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(LocalClassDeclarationStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(LocalRecordDeclarationStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(TypeParameter n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(UnaryExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(UnknownType n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(VariableDeclarationExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(VariableDeclarator n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(VoidType n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(WhileStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(WildcardType n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(LambdaExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(MethodReferenceExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(TypeExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(NodeList n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ImportDeclaration n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ModuleDeclaration n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ModuleRequiresDirective n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ModuleExportsDirective n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ModuleProvidesDirective n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ModuleUsesDirective n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ModuleOpensDirective n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(UnparsableStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(ReceiverParameter n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(VarType n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(Modifier n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(SwitchExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(TextBlockLiteralExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(YieldStmt n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(PatternExpr n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(RecordDeclaration n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(CompactConstructorDeclaration n, Object arg) {
        System.out.println(n.toString());
super.visit(n, arg);
    }

    @Override
    public void visit(LineComment n, Object arg) {
        n.remove();
    }

    @Override
    public void visit(BlockComment n, Object arg) {
        n.remove();
    }

    @Override
    public void visit(JavadocComment n, Object arg) {
        n.remove();
    }
}

