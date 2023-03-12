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

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author maber01
 */
public class LtiServiceScope
{
  public static final LtiServiceScope NRPS = new LtiServiceScope( "https://purl.imsglobal.org/spec/lti-nrps/claim/namesroleservice" );

  String specification;
  public LtiServiceScope( String specification )
  {
    if ( StringUtils.isBlank( specification ) )
      throw new IllegalArgumentException( "Blank specification for LtiServiceScope is not allowed." );
    this.specification = specification.trim();
  }
  
  public String getSpecification()
  {
    return specification;
  }
}
