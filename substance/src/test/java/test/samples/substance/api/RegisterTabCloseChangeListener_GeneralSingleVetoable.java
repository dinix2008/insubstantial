/*
 * Copyright (c) 2005-2010 Substance Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *  o Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer. 
 *     
 *  o Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution. 
 *     
 *  o Neither the name of Substance Kirill Grouchnikov nor the names of 
 *    its contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission. 
 *     
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */
package test.samples.substance.api;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.*;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.BusinessBlackSteelSkin;
import org.pushingpixels.substance.api.tabbed.VetoableTabCloseListener;

/**
 * Test application that shows the use of the
 * {@link SubstanceLookAndFeel#registerTabCloseChangeListener(org.pushingpixels.substance.api.tabbed.BaseTabCloseListener)}
 * API with registering a vetoable tab close listener that listens on single tab
 * closing on a specific tabbed pane.
 * 
 * @author Kirill Grouchnikov
 * @see SubstanceLookAndFeel#registerTabCloseChangeListener(org.pushingpixels.substance.api.tabbed.BaseTabCloseListener)
 */
public class RegisterTabCloseChangeListener_GeneralSingleVetoable extends
		JFrame {
	/**
	 * Creates the main frame for <code>this</code> sample.
	 */
	public RegisterTabCloseChangeListener_GeneralSingleVetoable() {
		super("Register tab close listener");

		this.setLayout(new BorderLayout());

		JTabbedPane jtp = new JTabbedPane();
		jtp.addTab("tab1", new JPanel());
		jtp.addTab("tab2", new JPanel());
		jtp.addTab("tab3", new JPanel());

		jtp.putClientProperty(
				SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_PROPERTY,
				Boolean.TRUE);

		// register tab close listener on all tabbed panes.
		SubstanceLookAndFeel
				.registerTabCloseChangeListener(new VetoableTabCloseListener() {
					@Override
                    public void tabClosing(JTabbedPane tabbedPane,
							Component tabComponent) {
						System.out.println("Tab "
								+ tabbedPane.getTitleAt(tabbedPane
										.indexOfComponent(tabComponent))
								+ " closing");
					}

					@Override
                    public void tabClosed(JTabbedPane tabbedPane,
							Component tabComponent) {
						System.out.println("Tab closed");
					}

					@Override
                    public boolean vetoTabClosing(JTabbedPane tabbedPane,
							Component tabComponent) {
						return (JOptionPane
								.showConfirmDialog(
										RegisterTabCloseChangeListener_GeneralSingleVetoable.this,
										"Are you sure you want to close "
												+ tabbedPane
														.getTitleAt(tabbedPane
																.indexOfComponent(tabComponent))
												+ "?") != JOptionPane.YES_OPTION);
					}
				});

		this.add(jtp, BorderLayout.CENTER);

		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * The main method for <code>this</code> sample. The arguments are ignored.
	 * 
	 * @param args
	 *            Ignored.
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
            public void run() {
				SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
				new RegisterTabCloseChangeListener_GeneralSingleVetoable()
						.setVisible(true);
			}
		});
	}
}
