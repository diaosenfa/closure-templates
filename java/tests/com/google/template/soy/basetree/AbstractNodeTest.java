/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.template.soy.basetree;

import com.google.template.soy.base.SourceLocation;
import com.google.template.soy.soytree.AbstractParentSoyNode;
import com.google.template.soy.soytree.SoyNode;

import junit.framework.*;


/**
 * Unit tests for AbstractNode.
 *
 */
public final class AbstractNodeTest extends TestCase {


  public void testAncestorMethods() {

    DummyNodeAlpha dummyA = new DummyNodeAlpha();
    SillyNode silly = new SillyNode();
    DopeyNode dopey = new DopeyNode();
    DummyNodeBeta dummyB = new DummyNodeBeta();
    SillyNode leaf = new SillyNode();

    dummyA.addChild(silly);
    silly.addChild(dummyB);
    silly.addChild(dopey);
    dummyB.addChild(leaf);

    assertTrue(leaf.hasAncestor(SillyNode.class));
    assertTrue(leaf.hasAncestor(DummyNode.class));
    assertTrue(leaf.hasAncestor(DummyNodeAlpha.class));
    assertTrue(leaf.hasAncestor(DummyNodeBeta.class));
    assertFalse(leaf.hasAncestor(DopeyNode.class));

    assertEquals(leaf, leaf.getNearestAncestor(SillyNode.class));
    assertEquals(silly, leaf.getParent().getNearestAncestor(SillyNode.class));
    assertEquals(dummyB, leaf.getNearestAncestor(DummyNode.class));
    assertEquals(dummyA, leaf.getNearestAncestor(DummyNodeAlpha.class));
    assertEquals(dummyB, leaf.getNearestAncestor(DummyNodeBeta.class));
    assertEquals(null, leaf.getNearestAncestor(DopeyNode.class));
  }


  private static interface DummyNode extends SoyNode {}

  private static final class DummyNodeAlpha extends AbstractParentSoyNode<SoyNode> implements DummyNode {
    DummyNodeAlpha() { super(1, SourceLocation.UNKNOWN); }
    @Override public String toSourceString() { return null; }
    @Override public DummyNodeAlpha clone() { throw new UnsupportedOperationException(); }
    @Override public Kind getKind() { return Kind.CALL_BASIC_NODE; }
  }

  private static final class DummyNodeBeta extends AbstractParentSoyNode<SoyNode> implements DummyNode {
    DummyNodeBeta() { super(2, SourceLocation.UNKNOWN); }
    @Override public String toSourceString() { return null; }
    @Override public DummyNodeBeta clone() { throw new UnsupportedOperationException(); }
    @Override public Kind getKind() { return Kind.CALL_BASIC_NODE; }
  }

  private static final class SillyNode extends AbstractParentSoyNode<SoyNode> {
    SillyNode() { super(3, SourceLocation.UNKNOWN); }
    @Override public String toSourceString() { return null; }
    @Override public SillyNode clone() { throw new UnsupportedOperationException(); }
    @Override public Kind getKind() { return Kind.CALL_BASIC_NODE; }
  }

  private static final class DopeyNode extends AbstractParentSoyNode<SoyNode> {
    DopeyNode() { super(4, SourceLocation.UNKNOWN); }
    @Override public String toSourceString() { return null; }
    @Override public DopeyNode clone() { throw new UnsupportedOperationException(); }
    @Override public Kind getKind() { return Kind.CALL_BASIC_NODE; }
  }

}
