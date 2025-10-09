import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import ExpandLessIcon from '@mui/icons-material/ExpandLess';
import '../../../styles/shared.css';

interface SidebarSectionProps {
  title: string;
  icon?: React.ReactNode;
  links: { label: string; to: string, element: React.ReactNode}[];
}

function SidebarSection({ title, icon, links }: SidebarSectionProps) {
	const [expanded, setExpanded] = useState(false);

	if(links.length < 1){
		return (
			<div className="sidebar-section-container">
				<div className="sidebar-section-header">
					{icon}
					<span>{title}</span>
				</div>
			</div>
		)
	}
	
	return (
		<div className="sidebar-section-container">
			<div className="sidebar-section-header" onClick={() => setExpanded(!expanded)}>
				{icon}
				<span>{title}</span>
				{expanded ? <ExpandLessIcon className="sidebar-expand-icon" /> : <ExpandMoreIcon className="sidebar-expand-icon" />}
			</div>
			{expanded && (<div className="sidebar-sub-link-container">
				{links.map((link) => (
					<div className="sidebar-sub-link">
						<Link key={link.to} className="sidebar-link" to={link.to}>
							{link.element}{link.label}
						</Link>
					</div>
				))}
			</div>
		)}
		</div>
	);
};

export default SidebarSection;