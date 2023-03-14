/*
 * Copyright 2023 maber01.
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
package uk.ac.leedsbeckett.lti.services;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author maber01
 */
public class LtiServiceScopeSet
{
  private final ArrayList<LtiServiceScope> specsInOrder = new ArrayList<>();
  String scopespec="";
  
  public void addScope( LtiServiceScope additionalscope )
  {
    if ( StringUtils.isBlank( additionalscope.getSpecification() ) || specsInOrder.contains( additionalscope ) )
      return;
    // if not already in, add the new scope
    specsInOrder.add( additionalscope );
    // make sure the scopes are sorted to ensure same set always gives same result
    specsInOrder.sort(( LtiServiceScope o1, LtiServiceScope o2 ) -> o1.specification.compareTo(o2.specification ));
    // concatenate scopes with space delimiter
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for ( LtiServiceScope scope : specsInOrder )
    {
      if ( !first ) sb.append( " " );
      first = false;
      sb.append( scope.getSpecification() );
    }
    // Store the spec
    scopespec = sb.toString();
  }
  
  public String getScopeSpecification()
  {
    return scopespec;
  }
}
