package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.*;

import java.util.Stack;

public class NumberOfMaxNestedBlock implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {

	private NestedBlock nestedBlock = new NestedBlock();
	private Stack<ASTNode> currentNode = new Stack<>();
	private Stack<Boolean> blocks = new Stack<>();
	private Stack<Boolean> nodes = new Stack<>();

	@Override
	public void visit(Block node) {

		// we always do a +1 if we see a block, with the exception of
		// a switch case, as we do the +1 in the block.
		// note that blocks might not always exist, and that's why we check
		// for their existence at every node later on...
		// if they do not exist, we +1 in the node.
		if(currentNode.empty() || !(currentNode.peek() instanceof SwitchCase)) {
			nestedBlock.plusOne();
			blocks.push(true);
		} else {
			blocks.push(false);
		}

		currentNode.push(node);
	}

	@Override
	public void visit(ForStatement node) {
		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getBody());
		if(!containsBlock) {
			nestedBlock.plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}

	}

	@Override
	public void visit(EnhancedForStatement node) {

		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getBody());
		if(!containsBlock) {
			nestedBlock.plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}

	}

	@Override
	public void visit(DoStatement node) {
		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getBody());
		if(!containsBlock) {
			nestedBlock.plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}

	}

	@Override
	public void visit(WhileStatement node) {
		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getBody());
		if(!containsBlock) {
			nestedBlock.plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}
	}

	@Override
	public void visit(SwitchStatement node) {

		currentNode.push(node);
		nodes.push(true);
		nestedBlock.plusOne();
	}

	@Override
	public void visit(SwitchCase node) {
		currentNode.push(node);
	}


	@Override
	public void visit(CatchClause node) {

		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getBody());
		if(!containsBlock) {
			nestedBlock.plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}

	}

	public void visit(IfStatement node) {

		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getThenStatement());
		if(!containsBlock) {
			nestedBlock.plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}
	}


	@Override
	public void endVisit(Block node) {
		Boolean pop = blocks.pop();
		if(pop)
			nestedBlock.setCurrent(nestedBlock.getCurrent() - 1);

		currentNode.pop();
	}

	@Override
	public void endVisit(IfStatement node) {
		nestedBlock.popBlock(this.nodes);
	}

	private boolean containsBlock(Statement body) {
		return (body instanceof Block);
	}


	@Override
	public void endVisit(CatchClause node) {
		nestedBlock.popBlock(this.nodes);
	}

	@Override
	public void endVisit(WhileStatement node) {
		nestedBlock.popBlock(this.nodes);
	}

	@Override
	public void endVisit(DoStatement node) {
		nestedBlock.popBlock(this.nodes);
	}

	@Override
	public void endVisit(EnhancedForStatement node) {
		nestedBlock.popBlock(this.nodes);
	}

	@Override
	public void endVisit(ForStatement node) {
		nestedBlock.popBlock(this.nodes);
	}

	@Override
	public void endVisit(SwitchStatement node) {
		nestedBlock.popBlock(this.nodes);
	}

	@Override
	public void setResult(CKMethodResult result) {
		// -1 because the method block is considered a block.
		// and we avoid 0, that can happen in case of enums
		result.setMaxNestedBlocks(Math.max(0, nestedBlock.getMax() - 1));
	}

	@Override
	public void setResult(CKClassResult result) {
		result.setMaxNestedBlocks(Math.max(0, nestedBlock.getMax() - 1));
	}
}
