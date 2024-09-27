/*
 * Copyright 2024 maber01.
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
package uk.ac.leedsbeckett.lti.services.ags.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigDecimal;
import java.time.Instant;
import uk.ac.leedsbeckett.lti.json.BigDecimalDeserializer;
import uk.ac.leedsbeckett.lti.json.BigDecimalSerializer;
import uk.ac.leedsbeckett.lti.json.Iso8601InstantDeserializer;
import uk.ac.leedsbeckett.lti.json.Iso8601InstantSerializer;

/**
 * Defines a line item which will contain multiple students' scores.
 * 
 * @author maber01
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class LineItem
{
  private final String id;
  private final BigDecimal scoreMaximum;
  private final String label;
  private final String resourceId;
  private final boolean gradesReleased;
  private final Instant startDateTime;
  private final Instant endDateTime;
  
  public LineItem( 
          @JsonProperty("id")                                                                        String     id, 
          @JsonProperty("scoreMaximum")   @JsonDeserialize(using = BigDecimalDeserializer.class)     BigDecimal scoreMaximum, 
          @JsonProperty("label")                                                                     String     label, 
          @JsonProperty("resourceId")                                                                String     resourceId, 
          @JsonProperty("gradesReleased")                                                            boolean    gradesReleased,
          @JsonProperty("startDateTime")  @JsonDeserialize(using = Iso8601InstantDeserializer.class) Instant    startDateTime,
          @JsonProperty("endDateTime")    @JsonDeserialize(using = Iso8601InstantDeserializer.class) Instant    endDateTime
        )
  {
    this.id = id;
    this.scoreMaximum = scoreMaximum;
    this.label = label;
    this.resourceId = resourceId;
    this.gradesReleased = gradesReleased;
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
  }

  public String getId()
  {
    return id;
  }

  @JsonSerialize(using = BigDecimalSerializer.class)
  public BigDecimal getScoreMaximum()
  {
    return scoreMaximum;
  }

  public String getLabel()
  {
    return label;
  }

  public String getResourceId()
  {
    return resourceId;
  }

  public boolean isGradesReleased()
  {
    return gradesReleased;
  }

  @JsonSerialize(using = Iso8601InstantSerializer.class)
  public Instant getStartDateTime()
  {
    return startDateTime;
  }

  @JsonSerialize(using = Iso8601InstantSerializer.class)
  public Instant getEndDateTime()
  {
    return endDateTime;
  }  
}
