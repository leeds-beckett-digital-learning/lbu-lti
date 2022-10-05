/*
 * Copyright 2022 Leeds Beckett University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.leedsbeckett.lti.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import uk.ac.leedsbeckett.lti.config.LtiConfiguration;
import uk.ac.leedsbeckett.lti.state.LtiState;
import uk.ac.leedsbeckett.lti.state.LtiStateStore;

/**
 * This abstract class is the superclass of LTI servlets so that they can
 * clearly declare the type of state class they use.
 * 
 * @author jon
 * @param <T>
 */
public abstract class LtiServlet<T extends LtiState> extends HttpServlet
{  
  /**
   * Implementations use this method to tell this object how to get the configuration.
   * 
   * @param context The servlet context.
   * @return An LtiConfiguration object.
   */
  protected abstract LtiConfiguration getLtiConfiguration( ServletContext context );
  
  /**
   * Implementations use this method to tell this object how to store state.
   * @param context The servlet context.
   * @return An LtiStateStore object.
   */
  protected abstract LtiStateStore<T> getLtiStateStore( ServletContext context );  
}
