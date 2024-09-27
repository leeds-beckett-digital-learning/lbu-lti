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
 * Contains one student's score for one lineitem.
 * 
 * https://www.imsglobal.org/spec/lti-ags/v2p0/openapi/#/default/Scores.POST
 * 
 * @author maber01
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Score
{
  private final String userId;
  private final BigDecimal scoreGiven;
  private final BigDecimal scoreMaximum;
  private final String comment;
  private final Instant timestamp;
  private final String activityProgress;
  private final String gradingProgress;
  private final Submission submission;

  public Score( 
          @JsonProperty("userId")                                                                      String     userId, 
          @JsonProperty("scoreGiven")       @JsonDeserialize(using = BigDecimalDeserializer.class)     BigDecimal scoreGiven, 
          @JsonProperty("scoreMaximum")     @JsonDeserialize(using = BigDecimalDeserializer.class)     BigDecimal scoreMaximum, 
          @JsonProperty("comment")                                                                     String     comment, 
          @JsonProperty("timestamp")        @JsonDeserialize(using = Iso8601InstantDeserializer.class) Instant    timestamp, 
          @JsonProperty("activityProgress")                                                            String     activityProgress, 
          @JsonProperty("gradingProgress")                                                             String     gradingProgress, 
          @JsonProperty("submission")                                                                  Submission submission
        )
  {
    this.userId = userId;
    this.scoreGiven = scoreGiven;
    this.scoreMaximum = scoreMaximum;
    this.comment = comment;
    this.timestamp = timestamp;
    this.activityProgress = activityProgress;
    this.gradingProgress = gradingProgress;
    this.submission = submission;
  }

  public String getUserId()
  {
    return userId;
  }

  @JsonSerialize(using = BigDecimalSerializer.class)
  public BigDecimal getScoreGiven()
  {
    return scoreGiven;
  }

  @JsonSerialize(using = BigDecimalSerializer.class)
  public BigDecimal getScoreMaximum()
  {
    return scoreMaximum;
  }

  public String getComment()
  {
    return comment;
  }

  @JsonSerialize(using = Iso8601InstantSerializer.class)
  public Instant getTimestamp()
  {
    return timestamp;
  }

  public String getActivityProgress()
  {
    return activityProgress;
  }

  public String getGradingProgress()
  {
    return gradingProgress;
  }

  public Submission getSubmission()
  {
    return submission;
  }
  
}
