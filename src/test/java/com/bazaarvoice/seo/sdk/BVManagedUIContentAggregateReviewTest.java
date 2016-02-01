/*
 * ===========================================================================
 * Copyright 2014 Bazaarvoice, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ===========================================================================
 *
 */

package com.bazaarvoice.seo.sdk;

import com.bazaarvoice.seo.sdk.config.BVClientConfig;
import com.bazaarvoice.seo.sdk.config.BVConfiguration;
import com.bazaarvoice.seo.sdk.config.BVSdkConfiguration;
import com.bazaarvoice.seo.sdk.helpers.SeoTestcaseConstants;
import com.bazaarvoice.seo.sdk.model.BVParameters;
import com.bazaarvoice.seo.sdk.model.ContentType;
import com.bazaarvoice.seo.sdk.model.SubjectType;
import com.bazaarvoice.seo.sdk.servlet.DefaultRequestContext;
import com.bazaarvoice.seo.sdk.util.BVMessageUtil;
import org.junit.Ignore;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test class for displaying only the aggregate reviews from the review
 * contents.
 *
 * @author Anandan Narayanaswamy
 */
public class BVManagedUIContentAggregateReviewTest {

  /**
   * Aggregate reivew test
   */
  @Test
  public void testSEOContentFromHTTP_SinglePagePRR_AggregateRating() {
    DefaultRequestContext.initialize();
    BVConfiguration bvConfig = new BVSdkConfiguration();
    bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "agileville-78B2EF7DE83644CAB5F8C72F2D8C8491"
    );
    bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "Main_Site-en_US"
    );

    BVUIContent uiContent = new BVManagedUIContent(bvConfig);

    BVParameters bvParameters = new BVParameters();
    bvParameters.setUserAgent("google");
    bvParameters.setContentType(ContentType.REVIEWS);
    bvParameters.setSubjectType(SubjectType.PRODUCT);
    bvParameters.setSubjectId("data-gen-5zkafmln4wymhcfbp5u6hmv5q");

    String theUIContent = uiContent.getAggregateRating(bvParameters);
    assertEquals(
      theUIContent.contains("itemprop=\"aggregateRating\" itemscope itemtype=\"http://schema.org/AggregateRating\">"),
      true,
      "there should be BvRRSourceID in the content"
    );
    assertEquals(
      !theUIContent.contains("itemprop=\"review\" itemscope itemtype=\"http://schema.org/Review\">"),
      true,
      "there should not be reviews section in the content"
    );
  }

  /**
   * review content test
   */
  @Test
  public void testSEOContentFromHTTP_SinglePagePRR_Reviews() {
    DefaultRequestContext.initialize();
    BVConfiguration bvConfig = new BVSdkConfiguration();
    bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "agileville-78B2EF7DE83644CAB5F8C72F2D8C8491"
    );
    bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "Main_Site-en_US"
    );

    BVUIContent uiContent = new BVManagedUIContent(bvConfig);

    BVParameters bvParameters = new BVParameters();
    bvParameters.setUserAgent("google");
    bvParameters.setContentType(ContentType.REVIEWS);
    bvParameters.setSubjectType(SubjectType.PRODUCT);
    bvParameters.setSubjectId("data-gen-5zkafmln4wymhcfbp5u6hmv5q");

    String theUIContent = uiContent.getReviews(bvParameters);
    assertEquals(
      !theUIContent.contains("<div id=\"bvseo-aggregateRatingSection\" itemprop=\"aggregateRating\" itemscope itemtype=\"http://schema.org/AggregateRating\">"),
      true,
      "there should not be aggregateRating section"
    );
    assertEquals(
      theUIContent.contains("itemprop=\"review\" itemscope itemtype=\"http://schema.org/Review\""),
      true,
      "there should be reviews section in the content"
    );
  }

  /**
   * User agent is google with call to aggregateRating and Review with same
   * bvparam.
   */
  @Ignore
  public void testSEOContent_SinglePageHTTP_aggregateRatingAndReviews() {
    DefaultRequestContext.initialize();
    BVConfiguration _bvConfig = new BVSdkConfiguration();
    _bvConfig.addProperty(
      BVClientConfig.SEO_SDK_ENABLED,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    _bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "myshco-126b543c32d9079f120a575ece25bad6"
    );
    _bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "9344ia"
    );

    BVParameters _bvParam = new BVParameters();
    _bvParam.setUserAgent("googlebot");
    _bvParam.setBaseURI("/thispage.htm");
    _bvParam.setPageURI("http://localhost:8080/abcd?notSure=1&letSee=2");
    _bvParam.setContentType(ContentType.REVIEWS);
    _bvParam.setSubjectType(SubjectType.PRODUCT);
    _bvParam.setSubjectId("5000001");

    BVUIContent _bvOutput = new BVManagedUIContent(_bvConfig);


    String sBvOutputSummary = _bvOutput.getAggregateRating(_bvParam);
    assertEquals(
      sBvOutputSummary.contains("<span itemprop=\"aggregateRating\" itemscope itemtype=\"http://schema.org/AggregateRating\">"),
      true,
      "there should be BvRRSourceID in the content"
    );
    assertEquals(
      !sBvOutputSummary.contains("itemprop=\"review\" itemscope itemtype=\"http://schema.org/Review\">"),
      true,
      "there should not be reviews section in the content"
    );

    String sBvOutputReviews = _bvOutput.getReviews(_bvParam);
    assertEquals(
      sBvOutputReviews.contains("itemprop=\"review\" itemscope itemtype=\"http://schema.org/Review\">"),
      true,
      "there should not be reviews section in the content"
    );
    assertEquals(
      !sBvOutputReviews.contains("<span itemprop=\"aggregateRating\" itemscope itemtype=\"http://schema.org/AggregateRating\">"),
      true,
      "there should be BvRRSourceID in the content"
    );
  }

  /**
   * Simulating getReviews ERR0013.
   *
   * Developer friendly test case. Agileville needs a page without reviews.
   * Punch in a content page without reviews that can be tested with this
   * test case.
   */
  @Ignore
  public void testSEOContent_SinglePageHTTP_getReviews_ERR0013() {
    DefaultRequestContext.initialize();
    BVConfiguration _bvConfig = new BVSdkConfiguration();
    _bvConfig.addProperty(
      BVClientConfig.SEO_SDK_ENABLED,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    _bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "agileville-78B2EF7DE83644CAB5F8C72F2D8C8491"
    );
    _bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "Main_Site-en_US"
    );

    BVParameters _bvParam = new BVParameters();
    _bvParam.setUserAgent("googlebot");
    _bvParam.setBaseURI("/thispage.htm");
    _bvParam.setPageURI("http://localhost:8080/abcd?notSure=1&letSee=2");
    _bvParam.setContentType(ContentType.REVIEWS);
    _bvParam.setSubjectType(SubjectType.PRODUCT);
    _bvParam.setSubjectId("5000002_NO_REVIEWS");

    BVUIContent _bvOutput = new BVManagedUIContent(_bvConfig);
    String sBvOutputSummary = _bvOutput.getReviews(_bvParam);

    assertEquals(
      !sBvOutputSummary.contains("itemprop=\"review\" itemscope itemtype=\"http://schema.org/Review\">"),
      true,
      "there should not be reviews section in the content"
    );
    assertEquals(
      !sBvOutputSummary.contains("<span itemprop=\"aggregateRating\" itemscope itemtype=\"http://schema.org/AggregateRating\">"),
      true,
      "there should be BvRRSourceID in the content");
    String expectedMessage = BVMessageUtil.getMessage("ERR0013");
    assertEquals(
      sBvOutputSummary.contains(expectedMessage),
      true,
      "Message does not contain expected message please test"
    );
  }

  /**
   * Simulating getReviews blank page test.
   * Developer friendly test case.
   */
  @Ignore
  public void testSEOContent_SinglePageHTTP_getReviews_Blank_page_test() {
    DefaultRequestContext.initialize();
    BVConfiguration _bvConfig = new BVSdkConfiguration();
    _bvConfig.addProperty(
      BVClientConfig.SEO_SDK_ENABLED,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    _bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "agileville-78B2EF7DE83644CAB5F8C72F2D8C8491"
    );
    _bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "Main_Site-en_US"
    );

    BVParameters _bvParam = new BVParameters();
    _bvParam.setUserAgent("googlebot");
    _bvParam.setBaseURI("/thispage.htm");
    _bvParam.setPageURI("http://localhost:8080/abcd?notSure=1&letSee=2");
    _bvParam.setContentType(ContentType.REVIEWS);
    _bvParam.setSubjectType(SubjectType.PRODUCT);
    _bvParam.setSubjectId("5000002_NO_BV");

    BVUIContent _bvOutput = new BVManagedUIContent(_bvConfig);
    String sBvOutputSummary = _bvOutput.getReviews(_bvParam);

    assertEquals(
      !sBvOutputSummary.contains("itemprop=\"review\" itemscope itemtype=\"http://schema.org/Review\">"),
      true,
      "there should not be reviews section in the content"
    );
    assertEquals(
      !sBvOutputSummary.contains("<span itemprop=\"aggregateRating\" itemscope itemtype=\"http://schema.org/AggregateRating\">"),
      true,
      "there should be BvRRSourceID in the content"
    );
    String expectedMessage = BVMessageUtil.getMessage("ERR0025");
    assertEquals(
      sBvOutputSummary.contains(expectedMessage),
      true,
      "Message does not contain expected message please test"
    );
  }

  /**
   * Bot detection is disabled and user agent is google but aggregate rating
   * is not present for this client.
   *
   * aggregate rating is populated for AgileVille hence punch in a content
   * page without aggregate rating and test. Developer friendly test case.
   */
  @Ignore
  public void testSEOContent_SinglePageHTTP_aggregateRating_IfNotPresent() {
    DefaultRequestContext.initialize();
    BVConfiguration _bvConfig = new BVSdkConfiguration();
    _bvConfig.addProperty(
      BVClientConfig.SEO_SDK_ENABLED,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    _bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "agileville-78B2EF7DE83644CAB5F8C72F2D8C8491"
    );
    _bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "Main_Site-en_US"
    );

    BVParameters _bvParam = new BVParameters();
    _bvParam.setUserAgent("google");
    _bvParam.setBaseURI("/thispage.htm");
    _bvParam.setPageURI("http://localhost:8080/abcd?notSure=1&letSee=2");
    _bvParam.setContentType(ContentType.REVIEWS);
    _bvParam.setSubjectType(SubjectType.PRODUCT);
    _bvParam.setSubjectId("5000002_No_Aggr_Rating");

    BVUIContent _bvOutput = new BVManagedUIContent(_bvConfig);

    String sBvOutputSummary = _bvOutput.getAggregateRating(_bvParam);
    assertEquals(
      !sBvOutputSummary.contains("<span itemprop=\"aggregateRating\" itemscope itemtype=\"http://schema.org/AggregateRating\">"),
      true,
      "there should be BvRRSourceID in the content"
    );
    assertEquals(
      !sBvOutputSummary.contains("itemprop=\"review\" itemscope itemtype=\"http://schema.org/Review\">"),
      true,
      "there should not be reviews section in the content"
    );
    String expectedMessage = BVMessageUtil.getMessage("ERR0003");
    assertEquals(
      sBvOutputSummary.contains(expectedMessage),
      true,
      "Message does not contain expected message please test"
    );

  }

  /**
   * Aggregate reviews without the pagination link.
   */
  @Test
  public void testAggregate_WithoutPagination() {
    DefaultRequestContext.initialize();
    BVConfiguration _bvConfig = new BVSdkConfiguration();
    _bvConfig.addProperty(
      BVClientConfig.SEO_SDK_ENABLED,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    _bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "agileville-78B2EF7DE83644CAB5F8C72F2D8C8491"
    );
    _bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "Main_Site-en_US"
    );

    BVParameters _bvParam = new BVParameters();
    _bvParam.setUserAgent("google");
    _bvParam.setBaseURI("/thispage.htm");
    _bvParam.setPageURI("http://localhost:8080/abcd?notSure=1&letSee=2");
    _bvParam.setContentType(ContentType.REVIEWS);
    _bvParam.setSubjectType(SubjectType.PRODUCT);
    _bvParam.setSubjectId("data-gen-5zkafmln4wymhcfbp5u6hmv5q");

    BVUIContent _bvOutput = new BVManagedUIContent(_bvConfig);

    String sBvOutputSummary = _bvOutput.getAggregateRating(_bvParam);
    assertEquals(
      sBvOutputSummary.contains("<!--begin-aggregate-rating-->"),
      true,
      "there should be aggregateRating in the content"
    );
    assertEquals(
      !sBvOutputSummary.contains("itemprop=\"review\" itemscope itemtype=\"http://schema.org/Review\">"),
      true,
      "there should not be reviews section in the content"
    );
  }

  /**
   * test case for get content when seo_sdk is disabled.
   */
  @Test
  public void testGetContent_SDK_Disabled() {
    DefaultRequestContext.initialize();
    BVConfiguration _bvConfig = new BVSdkConfiguration();
    _bvConfig.addProperty(
      BVClientConfig.SEO_SDK_ENABLED,
      "false"
    );
    _bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    _bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      SeoTestcaseConstants.getTestCloudKey()
    );
    _bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      SeoTestcaseConstants.getTestRootFolder()
    );

    BVParameters _bvParam = new BVParameters();
    _bvParam.setUserAgent("google");
    _bvParam.setBaseURI("/thispage.htm");
    _bvParam.setPageURI("http://localhost:8080/abcd?notSure=1&letSee=2");
    _bvParam.setContentType(ContentType.REVIEWS);
    _bvParam.setSubjectType(SubjectType.PRODUCT);
    _bvParam.setSubjectId(SeoTestcaseConstants.getTestReviewProduct_1());

    BVUIContent _bvOutput = new BVManagedUIContent(_bvConfig);

    String sBvOutputSummary = _bvOutput.getContent(_bvParam);
    assertEquals(
      sBvOutputSummary.contains("BVRRReviewsSoiSectionID"),
      false,
      "There should not be any reviews"
    );
    assertEquals(
      sBvOutputSummary.contains("<span itemprop=\"aggregateRating\" itemscope "),
      false,
      "There should not be any ratings"
    );
  }

  /**
   * Test case for getting Aggregate when seo_sdk is disabled.
   *
   * TODO: Fix this test case with valid assertion.
   * This is a bug and not valid until code changes are being made.
   */
  @Ignore
  public void testGetAggregateRating_SDK_Disabled() {
    DefaultRequestContext.initialize();
    BVConfiguration _bvConfig = new BVSdkConfiguration();
    _bvConfig.addProperty(
      BVClientConfig.SEO_SDK_ENABLED,
      "false"
    );
    _bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    _bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      SeoTestcaseConstants.getTestCloudKey()
    );
    _bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      SeoTestcaseConstants.getTestRootFolder()
    );

    BVParameters _bvParam = new BVParameters();
    _bvParam.setUserAgent("google");
    _bvParam.setBaseURI("/thispage.htm");
    _bvParam.setPageURI("http://localhost:8080/abcd?notSure=1&letSee=2");
    _bvParam.setContentType(ContentType.REVIEWS);
    _bvParam.setSubjectType(SubjectType.PRODUCT);
    _bvParam.setSubjectId(SeoTestcaseConstants.getTestReviewProduct_1());

    BVUIContent _bvOutput = new BVManagedUIContent(_bvConfig);
    String sBvOutputSummary = _bvOutput.getAggregateRating(_bvParam);
    assertEquals(
      sBvOutputSummary.contains("<li id=\"en\">bvseo-false</li>"),
      true,
      "There should be only footer string"
    );
    assertEquals(
      !sBvOutputSummary.contains("<!--begin-aggregate-rating-->"),
      true,
      "there should not be aggregateRating in the content"
    );
  }

  /**
   * test case to test get reviews when seo_sdk is disabled.
   *
   * TODO: Fix this test case with valid assertion.
   * This is a bug and not valid until code changes are being made.
   */
  @Ignore
  public void testGetReview_SDK_Disabled() {
    DefaultRequestContext.initialize();
    BVConfiguration _bvConfig = new BVSdkConfiguration();
    _bvConfig.addProperty(
      BVClientConfig.SEO_SDK_ENABLED,
      "false"
    );
    _bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    _bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      SeoTestcaseConstants.getTestCloudKey()
    );
    _bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );

    _bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      SeoTestcaseConstants.getTestRootFolder()
    );

    BVParameters _bvParam = new BVParameters();
    _bvParam.setUserAgent("google");
    _bvParam.setBaseURI("/thispage.htm");
    _bvParam.setPageURI("http://localhost:8080/abcd?notSure=1&letSee=2");
    _bvParam.setContentType(ContentType.REVIEWS);
    _bvParam.setSubjectType(SubjectType.PRODUCT);
    _bvParam.setSubjectId(SeoTestcaseConstants.getTestReviewProduct_1());

    BVUIContent _bvOutput = new BVManagedUIContent(_bvConfig);
    String sBvOutputSummary = _bvOutput.getReviews(_bvParam);
    assertEquals(
      sBvOutputSummary.contains("<li id=\"en\">bvseo-false</li>"),
      true,
      "There should only footer message"
    );
    assertEquals(
      sBvOutputSummary.contains("BVRRReviewsSoiSectionID"),
      false,
      "There should not be any review contents"
    );
  }

  /**
   * Crawler pattern change as user will enter any pattern and will be
   * straight string. If they are using separator, they will be using '|'
   * to separate words.
   */
  @Test
  public void testSEOContentFromHTTP_SinglePagePRR_CrawlerOverride() {
    DefaultRequestContext.initialize();
    BVConfiguration bvConfig = new BVSdkConfiguration();
    bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "agileville-78B2EF7DE83644CAB5F8C72F2D8C8491"
    );
    bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "Main_Site-en_US"
    );
    bvConfig.addProperty(
      BVClientConfig.CRAWLER_AGENT_PATTERN,
      "mysearchbot"
    );

    BVUIContent uiContent = new BVManagedUIContent(bvConfig);

    BVParameters bvParameters = new BVParameters();
    bvParameters.setUserAgent("111mysearchbot122");
    bvParameters.setContentType(ContentType.REVIEWS);
    bvParameters.setSubjectType(SubjectType.PRODUCT);
    bvParameters.setSubjectId("data-gen-5zkafmln4wymhcfbp5u6hmv5q");

    String theUIContent = uiContent.getAggregateRating(bvParameters);
    assertEquals(
      theUIContent.contains("<div id=\"bvseo-aggregateRatingSection\" itemprop=\"aggregateRating\" itemscope itemtype=\"http://schema.org/AggregateRating\">"),
      true,
      "there should be aggregateRating section"
    );
    assertEquals(
      !theUIContent.contains("itemprop=\"review\" itemscope itemtype=\"http://schema.org/Review\">"),
      true,
      "there should not be reviews section in the content"
    );

    /*
     * Scenario for multiple crawler pattern
     */
    bvConfig = new BVSdkConfiguration();
    bvConfig.addProperty(
      BVClientConfig.SSL_ENABLED,
      "true"
    );
    bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "agileville-78B2EF7DE83644CAB5F8C72F2D8C8491"
    );
    bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "Main_Site-en_US"
    );
    bvConfig.addProperty(
      BVClientConfig.CRAWLER_AGENT_PATTERN,
      "mysearchbot|anotherbot"
    );

    uiContent = new BVManagedUIContent(bvConfig);

    bvParameters = new BVParameters();
    bvParameters.setUserAgent("111mysearchbot122");
    bvParameters.setContentType(ContentType.REVIEWS);
    bvParameters.setSubjectType(SubjectType.PRODUCT);
    bvParameters.setSubjectId("data-gen-5zkafmln4wymhcfbp5u6hmv5q");

    theUIContent = uiContent.getAggregateRating(bvParameters);
    assertEquals(
      theUIContent.contains("<div id=\"bvseo-aggregateRatingSection\" itemprop=\"aggregateRating\" itemscope itemtype=\"http://schema.org/AggregateRating\">"),
      true,
      "there should be aggregateRating section"
    );
    assertEquals(
      !theUIContent.contains("itemprop=\"review\" itemscope itemtype=\"http://schema.org/Review\">"),
      true,
      "there should not be reviews section in the content"
    );

    bvParameters = new BVParameters();
    bvParameters.setUserAgent("111anotherbot122");
    bvParameters.setContentType(ContentType.REVIEWS);
    bvParameters.setSubjectType(SubjectType.PRODUCT);
    bvParameters.setSubjectId("data-gen-5zkafmln4wymhcfbp5u6hmv5q");

    theUIContent = uiContent.getAggregateRating(bvParameters);
    assertEquals(
      theUIContent.contains("<div id=\"bvseo-aggregateRatingSection\" itemprop=\"aggregateRating\" itemscope itemtype=\"http://schema.org/AggregateRating\">"),
      true,
      "there should be aggregateRating section"
    );
    assertEquals(
      !theUIContent.contains("itemprop=\"review\" itemscope itemtype=\"http://schema.org/Review\">"),
      true,
      "there should not be reviews section in the content"
    );
  }

  /**
   * When null subject id was set, there was no proper error message
   * displayed since the refactoring affected.
   */
  @Test
  public void testAggregate_NullSubjectID() {
    DefaultRequestContext.initialize();
    BVConfiguration _bvConfig = new BVSdkConfiguration();
    _bvConfig.addProperty(
      BVClientConfig.SEO_SDK_ENABLED,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    _bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      SeoTestcaseConstants.getTestCloudKey()
    );
    _bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      SeoTestcaseConstants.getTestRootFolder()
    );

    BVParameters _bvParam = new BVParameters();
    _bvParam.setUserAgent("google");
    _bvParam.setBaseURI("/thispage.htm");
    _bvParam.setPageURI("http://localhost:8080/abcd?notSure=1&letSee=2");
    _bvParam.setContentType(ContentType.REVIEWS);
    _bvParam.setSubjectType(SubjectType.PRODUCT);
    _bvParam.setSubjectId(null);

    BVUIContent _bvOutput = new BVManagedUIContent(_bvConfig);

    String sBvOutputSummary = _bvOutput.getAggregateRating(_bvParam);
    assertEquals(
      sBvOutputSummary.contains("<li data-bvseo=\"ms\">bvseo-msg: SubjectId cannot be null or empty.;</li>"),
      true,
      "there should be error message for SubjectId"
    );
    sBvOutputSummary = _bvOutput.getReviews(_bvParam);
    assertEquals(
      sBvOutputSummary.contains("<li data-bvseo=\"ms\">bvseo-msg: SubjectId cannot be null or empty.;</li>"),
      true,
      "there should be error message for SubjectId");
    sBvOutputSummary = _bvOutput.getContent(_bvParam);
    assertEquals(
      sBvOutputSummary.contains("<li data-bvseo=\"ms\">bvseo-msg: SubjectId cannot be null or empty.;</li>"),
      true,
      "there should be error message for SubjectId"
    );
  }

  /**
   * Testcase for invalid cloud key.
   */
  @Test
  public void testAggregate_InvalidURL() {
    DefaultRequestContext.initialize();
    BVConfiguration _bvConfig = new BVSdkConfiguration();
    _bvConfig.addProperty(
      BVClientConfig.SEO_SDK_ENABLED,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    _bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "invalid-2605f8e4ef6790962627644cc195acf2"
    );
    _bvConfig.addProperty(
      BVClientConfig.STAGING,
      "false"
    );
    _bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "11568-en_US"
    );

    BVParameters _bvParam = new BVParameters();
    _bvParam.setUserAgent("google");
    _bvParam.setBaseURI("/thispage.htm");
    _bvParam.setPageURI("http://localhost:8080/abcd?notSure=1&letSee=2");
    _bvParam.setContentType(ContentType.REVIEWS);
    _bvParam.setSubjectType(SubjectType.PRODUCT);
    _bvParam.setSubjectId("1577");

    BVUIContent _bvOutput = new BVManagedUIContent(_bvConfig);

    String sBvOutputSummary = _bvOutput.getAggregateRating(_bvParam);
    assertEquals(
      sBvOutputSummary.contains("<li data-bvseo=\"ms\">bvseo-msg: The resource to the URL or file is currently unavailable.;</li>"),
      true,
      "there should be error message for resource not found"
    );
    sBvOutputSummary = _bvOutput.getReviews(_bvParam);
    assertEquals(
      sBvOutputSummary.contains("<li data-bvseo=\"ms\">bvseo-msg: The resource to the URL or file is currently unavailable.;</li>"),
      true,
      "there should be error message for resource not found"
    );
    sBvOutputSummary = _bvOutput.getContent(_bvParam);
    assertEquals(
      sBvOutputSummary.contains("<li data-bvseo=\"ms\">bvseo-msg: The resource to the URL or file is currently unavailable.;</li>"),
      true,
      "there should be error message for resource not found"
    );
  }

  /**
   * If the page is completely blank we will check which error message is
   * thrown. This test case requires to create a blank page in the S3 folder
   * to test. Developer friendly test case.
   *
   * Use agileville and punch in a blank page with the product_id
   */
  @Ignore
  public void testSEOContent_BVContents_blank_page_AggregateRating() {
    DefaultRequestContext.initialize();
    /*
     * Test scenario when invoking getAggregateRating but receive blank
     * page.
     */

    BVConfiguration _bvConfig = new BVSdkConfiguration();
    _bvConfig.addProperty(
      BVClientConfig.SEO_SDK_ENABLED,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    _bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    _bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "agileville-78B2EF7DE83644CAB5F8C72F2D8C8491"
    );
    _bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "Main_Site-en_US"
    );

    BVParameters _bvParam = new BVParameters();
    _bvParam.setUserAgent("google");
    _bvParam.setBaseURI("/thispage.htm");
    _bvParam.setPageURI("http://localhost:8080/abcd?notSure=1&letSee=2");
    _bvParam.setContentType(ContentType.REVIEWS);
    _bvParam.setSubjectType(SubjectType.PRODUCT);
    _bvParam.setSubjectId("5000002_NO_BV");

    BVUIContent _bvOutput = new BVManagedUIContent(_bvConfig);

    String sBvOutputSummary = _bvOutput.getAggregateRating(_bvParam);
    assertEquals(
      !sBvOutputSummary.contains("itemprop=\"aggregateRating\" itemscope itemtype=\"http://schema.org/AggregateRating\">"),
      true,
      "there should not be aggregate section content"
    );
    assertEquals(
      !sBvOutputSummary.contains("itemprop=\"review\" itemscope itemtype=\"http://schema.org/Review\">"),
      true,
      "there should not be reviews section in the content");
    String expectedMessage = BVMessageUtil.getMessage("ERR0025");
    assertEquals(
      sBvOutputSummary.contains(expectedMessage),
      true,
      "Message does not contain expected message please test"
    );

  }

}