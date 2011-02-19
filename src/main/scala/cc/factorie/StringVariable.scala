/* Copyright (C) 2008-2010 University of Massachusetts Amherst,
   Department of Computer Science.
   This file is part of "FACTORIE" (Factor graphs, Imperative, Extensible)
   http://factorie.cs.umass.edu, http://code.google.com/p/factorie/
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */

package cc.factorie

trait StringVar extends Variable with VarAndValueGenericDomain[StringVar,String]

class StringVariable(initialValue:String) extends StringVar with MutableVar {
  private var _value: String = initialValue
  def value: String = _value
  def set(newValue:String)(implicit d:DiffList): Unit = {
    if (d ne null) d += new StringVariableDiff(_value, newValue)
    _value = newValue
  }
  case class StringVariableDiff(oldString:String, newString:String) extends Diff {
    @inline final def variable: StringVariable = StringVariable.this
    @inline final def redo = _value = newString
    @inline final def undo = _value = oldString
    override def toString = "StringVariableDiff("+oldString+","+newString+")"
  }
}