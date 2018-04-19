/*
 * Copyright (c) 2000-2018 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */

package com.teamdev.licensing.company;

import com.teamdev.licensing.LicenseDeliverable;
import com.teamdev.licensing.LicenseDeliverableVBuilder;
import com.teamdev.licensing.LicenseGenerationId;
import com.teamdev.licensing.LicenseGenerationIdVBuilder;
import com.teamdev.licensing.generation.LicenseDeliverableGenerated;
import com.teamdev.licensing.generation.LicenseDeliverableGeneratedVBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.teamdev.licensing.generation.given.LicenseGenerationTestEnv.productId;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Dmytro Dashenkov
 */
@DisplayName("LicenseDeliverableGenerated event should")
class CompanyWideLicenseGrantedTest
        extends CompanyWideLicenseEventReactionTest<LicenseDeliverableGenerated> {
    
    @Test
    @DisplayName("produce CompanyWideLicenseIssued event")
    void testEvent() {
        expectThat(new CompanyWideLicenseAggregate(id())).producesEvent(
                CompanyWideLicenseIssued.class,
                event -> {
                    assertEquals(message().getDeliverable(), event.getDeliverable());
                });
    }


    @Override
    protected LicenseDeliverableGenerated createMessage() {
        final LicenseDeliverable deliverable = LicenseDeliverableVBuilder.newBuilder()
                                                                         .setLicenseText("text")
                                                                         .build();
        final LicenseGenerationId id =
                LicenseGenerationIdVBuilder.newBuilder()
                                           .setCompanyWideLicenseId(id())
                                           .build();
        final LicenseDeliverableGeneratedVBuilder builder =
                LicenseDeliverableGeneratedVBuilder.newBuilder()
                                                   .setId(id)
                                                   .setDeliverable(deliverable)
                                                   .setProductId(productId());
        return builder.build();
    }
}
